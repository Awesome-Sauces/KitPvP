package me.alpha.kitpvp.utils;

import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Objects.ReduxPlayerObject.ReduxPlayer;
import org.bukkit.ChatColor;

public class ColorUtil {
    public static String colorCode(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String colorCode(String text, ReduxPlayer player){

        text = text.replaceAll("<level_username>", ChatManager.getLevelText(player.getPlayerObject()) +
                RankColor.getNameColor(player.getPlayerObject()) +
                ChatColor.stripColor(player.getPlayerObject().getDisplayName()));

        text = text.replaceAll("<username>", RankColor.getNameColor(player.getPlayerObject()) +
                ChatColor.stripColor(player.getPlayerObject().getDisplayName()));

        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
