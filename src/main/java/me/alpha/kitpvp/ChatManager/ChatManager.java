package me.alpha.kitpvp.ChatManager;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.XpData;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.IntegerHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.DecimalFormat;
import java.util.List;

public class ChatManager implements Listener {
    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event){
        if(event==null) return;

        String message = event.getMessage();
        Player player = event.getPlayer();

        message = message.replaceAll("%", "%%");

        String pitSupporter = "";

        if(player.hasPermission("pitSupporter")) pitSupporter = ColorUtil.colorCode("&e\u272C ");

        event.setFormat(getLevelPrestigeText(event.getPlayer())+pitSupporter+RankColor.getRankWithName(event.getPlayer())+RankColor.getChatColor(event.getPlayer())+": " + message);
    }

    public static void broadcastMessage(String message, World world){
        List<Player> players = world.getPlayers();

        if(!players.isEmpty()){
            for(Player messagePlayer : players){
                messagePlayer.sendMessage(message);
            }
        }
    }

    public static String getPlayerEXP(Player player){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return ChatColor.GRAY + " - " + ChatColor.AQUA + formatter.format(ClassInstances.xpData.getXp(player.getUniqueId().toString())) + " XP";
    }

    public static String getPlayerEXP(String player){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return ChatColor.GRAY + " - " + ChatColor.AQUA + formatter.format(ClassInstances.xpData.getXp(player)) + " XP";
    }

    public static String getLevelText(Player player){
        int[] playerData = XpData.GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = PrestigeBracketColors.getBracketColor(player);

        return prestigeColor + "[" + LevelColor.getLevelColor(level) + level + prestigeColor + "] ";
    }

    public static String getLevelPrestigeText(Player player){
        int[] playerData = XpData.GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = PrestigeBracketColors.getBracketColor(player);

        if(ClassInstances.prestigeData.getPrestige(player.getUniqueId().toString())<=0){
            return getBracketsWithLevel(player.getUniqueId().toString(), level);
        }

        return prestigeColor + "[" + ChatColor.YELLOW + String.valueOf(IntegerHelper.integerToRoman(ClassInstances.prestigeData.getPrestige(player.getUniqueId().toString()))) + PrestigeBracketColors.getBracketColor(player) + "-" + LevelColor.getLevelColor(level) + level + prestigeColor + "] ";
    }

    public static String getLevelPrestigeText(String player){
        int[] playerData = XpData.GetCurrentLevel(player, ClassInstances.xpData.getXp(player), ClassInstances.prestigeData.getPrestige(player));
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = PrestigeBracketColors.getBracketColor(player);

        if(ClassInstances.prestigeData.getPrestige(player)<=0){
            return getBracketsWithLevel(player, level);
        }

        return prestigeColor + "[" + ChatColor.YELLOW + String.valueOf(IntegerHelper.integerToRoman(ClassInstances.prestigeData.getPrestige(player))) + PrestigeBracketColors.getBracketColor(player) + "-" + LevelColor.getLevelColor(level) + level + prestigeColor + "] ";
    }

    public static String getBracketsWithLevel(String uuid, int level){

        int current_prestige = ClassInstances.prestigeData.getPrestige(uuid);
        String prestigeColor = PrestigeBracketColors.getBracketColor(uuid);

        //hasXp(uuid);

        //DecimalFormat formatter = new DecimalFormat("#,###");
        return prestigeColor + "[" + LevelColor.getLevelColor(level) + level + prestigeColor + "] ";
    }
}
