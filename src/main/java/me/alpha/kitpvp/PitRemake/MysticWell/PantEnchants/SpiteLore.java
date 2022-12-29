package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class SpiteLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getDefender().getPlayerObject()) &&
                event.getDefender().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefender().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("spite")) {
                if(event.getAttacker().getLeggings()!=null&&
                        event.getAttacker().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                    event.addReduxDamageMultiplier(5);
                }
            }


        }

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("spite")) {
                if(event.getDefender().getLeggings()!=null&&
                        event.getDefender().getLeggings().getType().equals(Material.LEATHER_LEGGINGS)){
                    event.addReduxDamageMultiplier(20);
                }
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

        return "&9Spite" + tier;
    }

    @Override
    public String lore(int level) {

        String lore = "&9Spite" + "\n" +
                "&7Deal &c+20% damage &7but receive\n" +
                "&c+5% damage &7versus players\n" +
                "&7wearing leather armor"
                + "\n&7";

        return colorCode(lore);
    }
}
