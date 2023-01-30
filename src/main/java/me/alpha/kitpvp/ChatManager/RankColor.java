package me.alpha.kitpvp.ChatManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RankColor {

    public static String getChatColor(Player player){
        String chatColor = ChatColor.GRAY.toString();

        if (player.isOp()) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("NARDUPE")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("PETER")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("LEFT")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("MVP++")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("VIP")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("MVP+")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("MVP")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("VIP+")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("VIP")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("HELPER")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("MOD")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("ADMIN")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("YOUTUBE")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("SALMON")) chatColor = String.valueOf(ChatColor.WHITE);
        else if(player.hasPermission("DEVELOPER")) chatColor = String.valueOf(ChatColor.WHITE);

        return chatColor;
    }

    public static String getNameColor(Player player){
        String nameColor = ChatColor.GRAY.toString();

        if (player==null) return nameColor;

        if (player.isOp()) nameColor = String.valueOf(ChatColor.RED);
        else if(player.hasPermission("NARDUPE")) nameColor = String.valueOf(ChatColor.RED);
        else if(player.hasPermission("PETER")) nameColor = String.valueOf(ChatColor.DARK_AQUA);
        else if(player.hasPermission("LEFT")) nameColor = String.valueOf(ChatColor.YELLOW);
        else if(player.hasPermission("MVP++")) nameColor = String.valueOf(ChatColor.GOLD);
        else if(player.hasPermission("VIP")) nameColor = String.valueOf(ChatColor.GREEN);
        else if(player.hasPermission("MVP+")) nameColor = String.valueOf(ChatColor.AQUA);
        else if(player.hasPermission("MVP")) nameColor = String.valueOf(ChatColor.AQUA);
        else if(player.hasPermission("VIP+")) nameColor = String.valueOf(ChatColor.GREEN);
        else if(player.hasPermission("VIP")) nameColor = String.valueOf(ChatColor.GREEN);
        else if(player.hasPermission("HELPER")) nameColor = String.valueOf(ChatColor.BLUE);
        else if(player.hasPermission("MOD")) nameColor = String.valueOf(ChatColor.DARK_GREEN);
        else if(player.hasPermission("ADMIN")) nameColor = String.valueOf(ChatColor.RED);
        else if(player.hasPermission("YOUTUBE")) nameColor = String.valueOf(ChatColor.RED);
        else if(player.hasPermission("SALMON")) nameColor = String.valueOf(ChatColor.RED);
        else if(player.hasPermission("DEVELOPER")) nameColor = String.valueOf(ChatColor.BLUE);


        return nameColor;
    }

    public static String getRankPrefix(Player player){
        String rankPrefix = "";

        if (player.isOp()) rankPrefix = ChatColor.RED + "[OWNER] ";
        else if(player.hasPermission("NARDUPE")) rankPrefix = ChatColor.RED + "[OWNER"  + ChatColor.RED + "] ";
        else if(player.hasPermission("PETER")) rankPrefix = ChatColor.translateAlternateColorCodes('&', "&3[&4You&atub&1er &bC&2r&9a&cc&6k&4e&ed] ");
        else if(player.hasPermission("LEFT")) rankPrefix = ChatColor.YELLOW + "[HUNT] ";
        else if(player.hasPermission("MVP++")) rankPrefix = ChatColor.GOLD + "[MVP" + ChatColor.BLACK + "++" + ChatColor.GOLD + "] ";
        else if(player.hasPermission("VIP")) rankPrefix = ChatColor.GREEN + "[VIP] ";
        else if(player.hasPermission("MVP+")) rankPrefix = ChatColor.AQUA + "[MVP" + ChatColor.RED + "+" + ChatColor.AQUA + "] ";
        else if(player.hasPermission("MVP")) rankPrefix = ChatColor.AQUA + "[MVP] ";
        else if(player.hasPermission("VIP+")) rankPrefix = ChatColor.GREEN + "[VIP" + ChatColor.WHITE + "+" + ChatColor.GREEN + "] ";
        else if(player.hasPermission("VIP")) rankPrefix = ChatColor.GREEN + "[VIP] ";
        else if(player.hasPermission("HELPER")) rankPrefix = ChatColor.BLUE + "[HELPER] ";
        else if(player.hasPermission("MOD")) rankPrefix = ChatColor.DARK_GREEN + "[MOD] ";
        else if(player.hasPermission("DEVELOPER")) rankPrefix = ChatColor.BLUE + "[DEV] ";
        else if(player.hasPermission("ADMIN")) rankPrefix = ChatColor.RED + "[ADMIN] ";
        else if(player.hasPermission("YOUTUBE")) rankPrefix = ChatColor.RED + "[" + ChatColor.WHITE + "YOUTUBE" + ChatColor.RED + "] ";
        else if(player.hasPermission("SALMON")) rankPrefix = ChatColor.translateAlternateColorCodes('&', "&4[&fS&4A&fL&4M&fO&4N&f]&4+&f+ ");

        return rankPrefix;
    }

    public static String getRankWithName(Player player){
        String rankPrefix = getRankPrefix(player);
        String nameColor = getNameColor(player);


        return rankPrefix+nameColor+ChatColor.stripColor(player.getDisplayName());
    }

    public static String getRankWithoutName(Player player){
        String rankPrefix = getRankPrefix(player);
        String nameColor = getNameColor(player);


        return rankPrefix;
    }
}
