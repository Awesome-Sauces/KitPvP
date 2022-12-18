package me.alpha.kitpvp.PitRemake.MysticWell.BowEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxBowEvent;
import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;

import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class ArrowArmoryLore extends PitEnchant {

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

        return "&9Arrow Armory" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(25*level);

        String lore = "&9Arrow Armory" + tier + "\n" +
                "&7Deals &c+"+multiplier+"% &7damage but uses &f5\n"+
                "&farrows &7per shot, if available"
                + "\n&7";

        return colorCode(lore);
    }
}
