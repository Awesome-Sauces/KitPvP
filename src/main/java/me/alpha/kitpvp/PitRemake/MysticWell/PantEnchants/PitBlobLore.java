package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class PitBlobLore  extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {}

    @Override
    public void init() {
        rarity = EnchantRarity.RARE;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&dRARE! &9Pit Blob" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String lore = "&dRARE! &9Pit Blob" + tier + "\n" +
                "&7Kills respawn &aThe Blob&7. This\n" +
                "&7slimy pet will follow you around\n" +
                "&7and kill your enemies. &aThe Blob\n" +
                "&7grows and gains health for every\n" +
                "&7enemy you kill." + "\n&7";

        return colorCode(lore);
    }
}