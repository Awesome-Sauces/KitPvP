package me.alpha.kitpvp.PitRemake.MysticWell.GlobalEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PantsRadarLore extends PitEnchant {
    @Override
    public void run(ReduxDamageEvent event) {

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Pants Radar" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(30*level);

        String lore = "&9Pants Radar" + tier + "\n" +
                "&7Pants, golden swords and enchanted\n" +
                "&7bows drop &d+"+multiplier+"% &7more frequently"+ "\n&7";

        return colorCode(lore);
    }
}

