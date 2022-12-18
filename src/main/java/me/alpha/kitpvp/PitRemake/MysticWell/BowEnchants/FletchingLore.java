package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class FletchingLore  extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {
    }

    public void bow(ReduxBowEvent event, int level){

    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Fletching" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(10+(level*3));

        String lore = "&9Fletching" + tier + "\n" +
                "&7Deal &c+"+multiplier+"% &7bow damage"
                + "\n&7";

        return colorCode(lore);
    }
}
