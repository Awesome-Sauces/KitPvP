package me.alpha.kitpvp.PitRemake.MysticWell.GlobalEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class GoldbumpLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {

    }

    public void run(ReduxDeathEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("goldbump")) {
                int level = item.getInteger("goldbump");
                double gold = level*4;
                event.addBaseGold((int) gold);
            }

        }

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null&&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("goldbump")) return;

            int level = item.getInteger("goldbump");
            double gold = level*4;
            event.addBaseGold((int) gold);


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

        return "&9Gold Bump" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf((2*level)+2);

        String lore = "&9Gold Bump" + tier + "\n" +
                "&7Earn &6+" + multiplier + "g&7 on kill" + "\n&7";

        return colorCode(lore);
    }
}
