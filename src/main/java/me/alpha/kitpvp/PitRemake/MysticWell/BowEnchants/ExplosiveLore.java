package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class ExplosiveLore extends PitEnchant {

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

        return "&dRARE! &9Explosive" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(5-(level));

        String lore = "&dRARE! &9Explosive" + tier + "\n" +
                "&7Arrows go BOOM! ("+multiplier+"s cooldown)"
                + "\n&7";

        return colorCode(lore);
    }
}