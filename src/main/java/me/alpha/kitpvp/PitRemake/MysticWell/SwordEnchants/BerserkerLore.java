package me.alpha.kitpvp.PitRemake.MysticWell.SwordEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class BerserkerLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {

    }

    @Override
    public void init() {
        EnchantRarity rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Berserker" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(level*10);

        String lore = "&9Berserker" + tier + "\n" +
                "&7You can now critical hit on the\n" +
                "&7ground. &a"+multiplier+"% chance &7to crit for\n" +
                "&c50% extra &7damage" + "\n&7";

        return colorCode(lore);
    }
}

