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


public class XpboostLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {

    }

    public void run(ReduxDeathEvent event){

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("xpboost")) {
                int level = item.getInteger("xpboost");

                int xp = (10*level);
                event.addXpIncrease(xp);
            }

        }

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("xpboost")) return;

            int level = item.getInteger("xpboost");

            int xp = (10*level);
            event.addXpIncrease(xp);


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

        return "&9XP Boost" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(10*level);

        String lore = "&9XP Boost" + tier + "\n" +
                "&7Earn &b+" + multiplier + "% XP&7 from kills" + "\n&7";

        return colorCode(lore);
    }
}
