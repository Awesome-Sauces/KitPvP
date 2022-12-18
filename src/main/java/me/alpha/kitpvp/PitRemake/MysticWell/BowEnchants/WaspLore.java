package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;
import org.bukkit.potion.PotionEffectType;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class WaspLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("wasp")) return;

            int level = item.getInteger("wasp");

            event.getAttacker().fShotCount++;

            int multiplier = level+1;
            int seconds = 5*level;

            event.getDefender().addPotionEffect(PotionEffectType.WEAKNESS, seconds, multiplier);

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

        return "&9Wasp" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(integerToRoman(level+1));
        String seconds = String.valueOf(5*level);

        String lore = "&9Wasp" + tier + "\n" +
                "&7Apply &cWeakness " + multiplier + " &7("+seconds+"s) on hit"
                + "\n&7";

        return colorCode(lore);
    }
}

