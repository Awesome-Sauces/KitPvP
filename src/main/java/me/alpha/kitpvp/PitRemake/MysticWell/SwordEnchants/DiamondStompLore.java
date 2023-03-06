package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;


import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;


public class DiamondStompLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("diamondstomp")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("diamondstomp");

        double multiplier = 0;

        if (level > 2) {
            multiplier += (level * 8) + 1;
        }else {multiplier += level*6;}


        if(event.getDefender().getHelmet() != null && event.getDefender().getHelmet().getType().equals(Material.DIAMOND_HELMET)
                || event.getDefender().getChestplate() != null &&  event.getDefender().getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE)
                || event.getDefender().getLeggings() != null && event.getDefender().getLeggings().getType().equals(Material.DIAMOND_LEGGINGS)
                || event.getDefender().getBoots() != null && event.getDefender().getBoots().getType().equals(Material.DIAMOND_BOOTS))
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

        return "&9Diamond Stomp" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier += String.valueOf((level * 8) + 1);
        }else {multiplier += String.valueOf(level*6);}

        String lore = "&9Diamond Stomp" + tier + "\n" +
                "&7Deal &c" + multiplier + "%&7 damage vs. players" +
                "\n&7wearing diamond armor" + "\n&7";

        return colorCode(lore);
    }
}
