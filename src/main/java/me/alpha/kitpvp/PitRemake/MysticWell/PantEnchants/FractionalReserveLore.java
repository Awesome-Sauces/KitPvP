package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;


import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class FractionalReserveLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()!=null){
            NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());

            if(!item.hasKey("fractionalreserve")) return;


        }else if(!CitizensHelper.isNPC(event.getDefenders().getPlayerObject()) &&
                event.getDefenders().getPlayerObject().getInventory().getLeggings()==null) return;
        else if(CitizensHelper.isNPC(event.getDefenders().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getDefenders().getPlayerObject().getInventory().getLeggings());
        int level = item.getInteger("fractionalreserve");

        if (event.getDefenders().getPlayerGold() < 50000) return;

        double damage = (7+((level-1)*15));

        double playerGold = event.getDefenders().getPlayerGold();

        int goldTimes = (int) Math.round(playerGold/50000);

        event.subtractReduxDamageMultiplier(Math.max(Math.min(goldTimes, damage),
                1));

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Fractional Reserve" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(7+((level-1)*15));

        String lore = "&9Fractional Reserve" + tier + "\n" +
                "&7Receive &9-1% damage&7 per\n" +
                "&650,000g &7you have (&9-" + multiplier + "%\n" +
                "&7max)" + "\n&7";

        return colorCode(lore);
    }
}