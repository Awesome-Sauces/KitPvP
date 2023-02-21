package me.alpha.kitpvp.PitRemake.MysticWell.GlobalEnchants;


import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDeathEvent;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import me.alpha.kitpvp.utils.IntegerHelper;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class GoldboostLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {

    }

    public void run(ReduxDeathEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("goldboost")) {
                int level = item.getInteger("goldboost");

                double gold = 15*level;
                event.addGoldIncrease((int) gold);
            }

        }

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("goldboost")) return;

            int level = item.getInteger("goldboost");

            double gold = 15*level;
            event.addGoldIncrease((int) gold);


        }
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + IntegerHelper.integerToRoman(level);}

        return "&9Gold Boost" + tier;
    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + IntegerHelper.integerToRoman(level);}

        String multiplier = String.valueOf(15*level);

        String lore = "&9Gold Boost" + tier + "\n" +
                "&7Earn &6+" + multiplier + "% gold (g)&7 from kill" + "\n&7";

        return colorCode(lore);
    }
}
