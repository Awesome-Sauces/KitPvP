package me.alpha.kitpvp.utils;

import org.bukkit.ChatColor;

public class ColorUtil {
    public static String colorCode(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
