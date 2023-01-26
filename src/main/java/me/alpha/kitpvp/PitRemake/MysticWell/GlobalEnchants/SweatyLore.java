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

public class SweatyLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {

    }

    public void run(ReduxDeathEvent event){


        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getInventory().getLeggings());

            if(item.hasKey("sweaty")) {
                int level = item.getInteger("sweaty");

                int xp = (20*level);
                int xp_cap = (level-1)*50;

                event.setXp_cap(event.getXp_cap()+xp_cap);
                event.addXpIncrease(xp);
            }

        }

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null &&
                event.getAttacker().getPlayerObject().getItemInHand().getType()!= Material.AIR){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("sweaty")) return;

            int level = item.getInteger("sweaty");

            int xp = (20*level);
            int xp_cap = (level-1)*50;

            event.setXp_cap(event.getXp_cap()+xp_cap);
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

        return "&9Sweaty" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(15*level);

        String max = String.valueOf(25*(level+1));

        String lore = "&9Sweaty" + tier + "\n" +
                "&7Earn &b+" + multiplier + "% XP&7 from streak XP\n" +
                "&bbonus and &b+" + max + " max XP&7 per kill" + "\n&7";

        return colorCode(lore);
    }
}
