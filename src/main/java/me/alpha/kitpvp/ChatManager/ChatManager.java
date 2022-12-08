package me.alpha.kitpvp.ChatManager;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.Data.XpData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {
    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event){
        event.setFormat(RankColor.getRankWithName(event.getPlayer())+RankColor.getChatColor(event.getPlayer())+": " + event.getMessage());
    }

    public static String getLevelText(Player player){
        int[] playerData = XpData.GetCurrentLevel(String.valueOf(player.getUniqueId()), ClassInstances.xpData.getXp(String.valueOf(player.getUniqueId())), ClassInstances.prestigeData.getPrestige(String.valueOf(player.getUniqueId())), player);
        int level = playerData[1];
        int neededXP = playerData[0];

        String prestigeColor = PrestigeBracketColors.getBracketColor(player);

        return prestigeColor + "[" + LevelColor.getLevelColor(level) + level + prestigeColor + "] ";
    }
}
