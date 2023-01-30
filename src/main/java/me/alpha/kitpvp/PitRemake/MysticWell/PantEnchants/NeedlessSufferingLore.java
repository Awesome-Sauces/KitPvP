package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class NeedlessSufferingLore extends PitEnchant {

    public void run(ReduxDeathEvent event){

        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("suffering")) {
                CraftPlayer craftAttacker = (CraftPlayer) event.getDefender().getPlayerObject(); //CraftBukkit
                EntityHuman entityAttacker = craftAttacker.getHandle(); //NMS

                //EntityHuman entityDefender = craftDefender.getHandle(); //NMS
                entityAttacker.setAbsorptionHearts(60);
            }


        }
    }

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("misery")) {
                int level = item.getInteger("misery");

            }


        }

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("misery")) {
                int level = item.getInteger("misery");
            }


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

        return "&9Needless Suffering" + tier;
    }

    @Override
    public String lore(int level) {

        String lore = "&9Needless Suffering" + "\n" +
                "&7Respawn with &630\u2764 &7absorption.\n" +
                "&cCannot heal!"
                + "\n&7";

        return colorCode(lore);
    }
}
