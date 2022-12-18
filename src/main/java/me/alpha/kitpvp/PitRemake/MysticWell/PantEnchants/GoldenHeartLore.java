package me.alpha.kitpvp.PitRemake.MysticWell.PantEnchants;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import me.alpha.kitpvp.PitRemake.MysticWell.EnchantRarity;
import me.alpha.kitpvp.PitRemake.MysticWell.PitEnchant;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import static me.alpha.kitpvp.utils.IntegerHelper.integerToRoman;

public class GoldenHeartLore extends PitEnchant {

    @Override
    public void run(ReduxDamageEvent event) {}

    private double getMultiplier(Player player, double multiplier){

        double power = 0;

        for(Entity entity : player.getNearbyEntities(15, 15, 15))
            if(entity instanceof Player) power += multiplier;

        return Math.min(power, multiplier * 10);
    }

    @Override
    public void init() {
        rarity = EnchantRarity.NORMAL;
    }

    @Override
    public String title(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        return "&9Golden Heart" + tier;
    }

    @Override
    public String lore(int level) {
        String tier = "";
        if (level > 1){tier += " " + integerToRoman(level);}

        String multiplier = String.valueOf(1+((level-1)*.5));

        String lore = "&9Golden Heart" + tier + "\n" +
                "&7Gain &6+"+multiplier+"\u2764&7 absorption on kill\n" +
                "&7(max &6\u2764&7)" + "\n&7";

        return colorCode(lore);
    }
}