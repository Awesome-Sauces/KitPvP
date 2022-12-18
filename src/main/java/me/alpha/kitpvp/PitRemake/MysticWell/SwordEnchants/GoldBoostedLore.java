package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class GoldBoostedLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("goldandboosted")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("goldandboosted");

        CraftPlayer craftAttacker = (CraftPlayer) event.getAttacker().getPlayerObject(); //CraftBukkit
        EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

        //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
        double abs = entityAttacker.getAbsorptionHearts();
        if(abs>0){
            double damage = (double) (level*5);

            event.addReduxDamageMultiplier(damage);
        }
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Gold and Boosted" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*5);

        String lore = "&9Gold and Boosted" + tier + "\n" +
                "&7Deal &c+" + multiplier + "% &7damage when you have\n" +
                "&7absorption hearts" + "\n&7";

        return colorCode(lore);
    }
}

