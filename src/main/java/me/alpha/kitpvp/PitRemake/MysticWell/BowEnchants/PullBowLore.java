package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PullBowLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event, int level){

    }

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Pullbow" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(8-(level+1));

        String lore = "&dRARE! &9Pullbow" + tier + "\n" +
                "&7Hitting a player pulls them and\n" +
                "&7nearby players toward you ("+multiplier+"s" +
                "&7cooldown)" + "\n&7";

        return colorCode(lore);
    }
}

