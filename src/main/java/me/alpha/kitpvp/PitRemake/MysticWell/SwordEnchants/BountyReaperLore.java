package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class BountyReaperLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("bountyreaper")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("bountyreaper");

        double damage = (level*7);
        event.addReduxDamageMultiplier((damage));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Bounty Reaper" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*7);

        String lore = "&9Bounty Reaper" + tier + "\n" +
                "&7Deal &c" + multiplier + "%&7 more damage to bountied players" + "\n&7";

        return colorCode(lore);
    }
}
