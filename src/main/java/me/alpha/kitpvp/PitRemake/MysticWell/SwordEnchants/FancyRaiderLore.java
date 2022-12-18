package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;


public class FancyRaiderLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("fancyraider")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("fancyraider");

        double multiplier = 0;

        if (level > 2) {
            multiplier += (level * 5);
        }else {multiplier += level*5;}


        if(event.getDefenders().getHelmet() != null && event.getDefenders().getHelmet().getType().equals(Material.LEATHER_HELMET)
                || event.getDefenders().getChestplate() != null &&  event.getDefenders().getChestplate().getType().equals(Material.LEATHER_CHESTPLATE)
                || event.getDefenders().getLeggings() != null && event.getDefenders().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)
                || event.getDefenders().getBoots() != null && event.getDefenders().getBoots().getType().equals(Material.LEATHER_BOOTS))
            event.addReduxDamageMultiplier(multiplier);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Fancy Raider" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf(level * 5);
        }else {multiplier += String.valueOf(level*5);}

        String lore = "&9Fancy Raider" + tier + "\n" +
                "&7Deal &c" + multiplier + "%&7 damage vs. players" +
                "\n&7wearing leather armor" + "\n&7";

        return colorCode(lore);
    }
}
