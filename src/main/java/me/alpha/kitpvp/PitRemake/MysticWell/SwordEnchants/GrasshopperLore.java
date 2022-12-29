package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;
import org.bukkit.Material;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class GrasshopperLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("grasshopper")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("grasshopper");

        if(event.getAttacker().getPlayerObject().getWorld().getBlockAt(event.getAttacker().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS) ||
                event.getDefender().getPlayerObject().getWorld().getBlockAt(event.getDefender().getPlayerObject().getLocation().add(0,-1,0)).getType().equals(Material.GRASS)){
            double damage = (level*5);
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

        return "&9Grasshopper" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*5);

        String lore = "&9Grasshopper" + tier + "\n" +
                "&7Deal &c+" + multiplier + "% &7damage when you or\n" +
                "&7your victim are standing on grass" + "\n&7";

        return colorCode(lore);
    }
}

