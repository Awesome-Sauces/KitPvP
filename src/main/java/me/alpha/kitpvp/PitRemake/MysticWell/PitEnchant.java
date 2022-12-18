package me.alpha.kitpvp.PitRemake.MysticWell;

import me.alpha.kitpvp.CustomEvents.ReduxDamageEvent;
import org.bukkit.ChatColor;

public abstract class PitEnchant {

    public EnchantRarity rarity;

    public String colorCode(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public abstract void run(ReduxDamageEvent event);

    public abstract String title(int level);

    public abstract void init();

    public abstract String lore(int level);
}
