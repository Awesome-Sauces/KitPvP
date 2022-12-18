package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import de.tr7zw.nbtapi.NBTItem;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import me.alpha.kitpvp.utils.CitizensHelper;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PainFocusLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

        if (!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null){
            NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());

            if(!item.hasKey("painfocus")) return;


        }else if(!CitizensHelper.isNPC(event.getAttacker().getPlayerObject()) &&
                event.getAttacker().getPlayerObject().getItemInHand()!=null) return;
        else if(CitizensHelper.isNPC(event.getAttacker().getPlayerObject())) return;

        NBTItem item = new NBTItem(event.getAttacker().getPlayerObject().getItemInHand());
        int level = item.getInteger("painfocus");

        double multiplier = 0;

        if (level > 2) {
            multiplier = level+2;
        }else{multiplier = level;}

        int maxHealth = (int) event.getAttacker().getPlayerObject().getMaxHealth();
        int currentHealth = (int) event.getAttacker().getPlayerObject().getHealth();

        int hearts = Math.max((maxHealth-currentHealth), 1);

        event.addReduxDamageMultiplier((hearts*(multiplier)));
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Pain Focus" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = "";

        if (level > 2) {
            multiplier = String.valueOf(level+2);
        }else{multiplier = String.valueOf(level);}

        String lore = "&9Pain Focus" + tier + "\n" +
                "&7Deal &c+" + multiplier +  "%&7 damage per &c\u2764" + "\n&7you're missing" + "\n&7";

        return colorCode(lore);
    }
}
