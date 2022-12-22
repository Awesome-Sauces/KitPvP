package me.alpha.kitpvp.PitRemake.Leaderboards;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.ChatManager.RankColor;
import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.PitCommands.View.ViewCore;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.net.URL;
import java.util.*;

import static me.alpha.kitpvp.PitRemake.Locations.getLeaderBoardLocation;

public class Leaderboard {

    public static HashMap<Integer, ArmorStand> LeaderBoard = new HashMap<>();

    private static Hologram hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("world")));
    private static Hologram hologram2 = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("lobby")));
    private static Hologram hologram3 = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("lobby2")));

    public static void TopPlayers(){

            List<Map.Entry<String, Integer>> topPlayers = mainGetTop();

            String top1 = "null";
            String top2 = "null";
            String top3 = "null";
            String top4 = "null";
            String top5 = "null";
            String top6 = "null";
            String top7 = "null";
            String top8 = "null";
            String top9 = "null";
            String top10 = "null";
            Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        Player player4 = null;
        Player player5 = null;
        Player player6 = null;
        Player player7 = null;
        Player player8 = null;
        Player player9 = null;
        Player player10 = null;


            if (topPlayers.get(9).getKey() != null){
                top1 = getName(topPlayers.get(9).getKey());
                Player tempPlayer = Bukkit.getPlayer(top1);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top1).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player1=tempPlayer;
            }

            if (topPlayers.get(8).getKey() != null){
                top2 = getName(topPlayers.get(8).getKey());
                Player tempPlayer = Bukkit.getPlayer(top2);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top2).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player2=tempPlayer;
            }

            if (topPlayers.get(7).getKey() != null){
                top3 = getName(topPlayers.get(7).getKey());
                Player tempPlayer = Bukkit.getPlayer(top3);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top3).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player3=tempPlayer;
            }

            if (topPlayers.get(6).getKey() != null){
                top4 = getName(topPlayers.get(6).getKey());
                Player tempPlayer = Bukkit.getPlayer(top4);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top4).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player4=tempPlayer;
            }

            if (topPlayers.get(5).getKey() != null){
                top5 = getName(topPlayers.get(5).getKey());
                Player tempPlayer = Bukkit.getPlayer(top5);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top5).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player5=tempPlayer;
            }

            if (topPlayers.get(4).getKey() != null){
                top6 = getName(topPlayers.get(4).getKey());
                Player tempPlayer = Bukkit.getPlayer(top6);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top6).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player6=tempPlayer;
            }

            if (topPlayers.get(3).getKey() != null){
                top7 = getName(topPlayers.get(3).getKey());
                Player tempPlayer = Bukkit.getPlayer(top7);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top7).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player7=tempPlayer;
            }

            if (topPlayers.get(2).getKey() != null){
                top8 = getName(topPlayers.get(2).getKey());
                Player tempPlayer = Bukkit.getPlayer(top8);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top8).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player8=tempPlayer;
            }

            if (topPlayers.get(1).getKey() != null){
                top9 = getName(topPlayers.get(1).getKey());
                Player tempPlayer = Bukkit.getPlayer(top9);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top9).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player9=tempPlayer;
            }

            if (topPlayers.get(0).getKey() != null){
                top10 = getName(topPlayers.get(0).getKey());
                Player tempPlayer = Bukkit.getPlayer(top10);
                OfflinePlayer offline;

                if(tempPlayer==null){
                    offline = Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(top10).getUniqueId());
                    tempPlayer = ViewCore.loadPlayer(offline);
                }
                player10=tempPlayer;
            }


            //For every player, add their name to gui
            hologram.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "1. " + ChatManager.getLevelPrestigeText(player1) + RankColor.getNameColor(player1) + ChatColor.stripColor(player1.getDisplayName()) + ChatManager.getPlayerEXP(player1) );
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "2. " + ChatManager.getLevelPrestigeText(player2) + RankColor.getNameColor(player2) + ChatColor.stripColor(player2.getDisplayName()) + ChatManager.getPlayerEXP(player2));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "3. " + ChatManager.getLevelPrestigeText(player3) + RankColor.getNameColor(player3) + ChatColor.stripColor(player3.getDisplayName()) + ChatManager.getPlayerEXP(player3));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "4. " + ChatManager.getLevelPrestigeText(player4) + RankColor.getNameColor(player4) + ChatColor.stripColor(player4.getDisplayName()) + ChatManager.getPlayerEXP(player4));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "5. " + ChatManager.getLevelPrestigeText(player5) + RankColor.getNameColor(player5) + ChatColor.stripColor(player5.getDisplayName()) + ChatManager.getPlayerEXP(player5));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "6. " + ChatManager.getLevelPrestigeText(player6) + RankColor.getNameColor(player6) + ChatColor.stripColor(player6.getDisplayName()) + ChatManager.getPlayerEXP(player6));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "7. " + ChatManager.getLevelPrestigeText(player7) + RankColor.getNameColor(player7) + ChatColor.stripColor(player7.getDisplayName()) + ChatManager.getPlayerEXP(player7));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "8. " + ChatManager.getLevelPrestigeText(player8) + RankColor.getNameColor(player8) + ChatColor.stripColor(player8.getDisplayName()) + ChatManager.getPlayerEXP(player8));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "9. " + ChatManager.getLevelPrestigeText(player9) + RankColor.getNameColor(player9) + ChatColor.stripColor(player9.getDisplayName()) + ChatManager.getPlayerEXP(player9));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "10. " + ChatManager.getLevelPrestigeText(player10) + RankColor.getNameColor(player10) + ChatColor.stripColor(player10.getDisplayName()) + ChatManager.getPlayerEXP(player10));
            hologram.appendTextLine("");

        //For every player, add their name to gui
            hologram2.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
            hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "1. " + ChatManager.getLevelPrestigeText(player1) + RankColor.getNameColor(player1) + ChatColor.stripColor(player1.getDisplayName())  + ChatManager.getPlayerEXP(player1));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "2. " + ChatManager.getLevelPrestigeText(player2) + RankColor.getNameColor(player2) + ChatColor.stripColor(player2.getDisplayName()) + ChatManager.getPlayerEXP(player2));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "3. " + ChatManager.getLevelPrestigeText(player3) + RankColor.getNameColor(player3) + ChatColor.stripColor(player3.getDisplayName()) + ChatManager.getPlayerEXP(player3));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "4. " + ChatManager.getLevelPrestigeText(player4) + RankColor.getNameColor(player4) + ChatColor.stripColor(player4.getDisplayName()) + ChatManager.getPlayerEXP(player4));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "5. " + ChatManager.getLevelPrestigeText(player5) + RankColor.getNameColor(player5) + ChatColor.stripColor(player5.getDisplayName()) + ChatManager.getPlayerEXP(player5));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "6. " + ChatManager.getLevelPrestigeText(player6) + RankColor.getNameColor(player6) + ChatColor.stripColor(player6.getDisplayName()) + ChatManager.getPlayerEXP(player6));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "7. " + ChatManager.getLevelPrestigeText(player7) + RankColor.getNameColor(player7) + ChatColor.stripColor(player7.getDisplayName()) + ChatManager.getPlayerEXP(player7));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "8. " + ChatManager.getLevelPrestigeText(player8) + RankColor.getNameColor(player8) + ChatColor.stripColor(player8.getDisplayName()) + ChatManager.getPlayerEXP(player8));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "9. " + ChatManager.getLevelPrestigeText(player9) + RankColor.getNameColor(player9) + ChatColor.stripColor(player9.getDisplayName()) + ChatManager.getPlayerEXP(player9));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "10. " + ChatManager.getLevelPrestigeText(player10) + RankColor.getNameColor(player10) + ChatColor.stripColor(player10.getDisplayName()) + ChatManager.getPlayerEXP(player10));
            hologram2.appendTextLine("");

        //For every player, add their name to gui
        hologram3.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "1. " + ChatManager.getLevelPrestigeText(player1) + RankColor.getNameColor(player1) + ChatColor.stripColor(player1.getDisplayName()) + ChatManager.getPlayerEXP(player1) );
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "2. " + ChatManager.getLevelPrestigeText(player2) + RankColor.getNameColor(player2) + ChatColor.stripColor(player2.getDisplayName()) + ChatManager.getPlayerEXP(player2));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "3. " + ChatManager.getLevelPrestigeText(player3) + RankColor.getNameColor(player3) + ChatColor.stripColor(player3.getDisplayName()) + ChatManager.getPlayerEXP(player3));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "4. " + ChatManager.getLevelPrestigeText(player4) + RankColor.getNameColor(player4) + ChatColor.stripColor(player4.getDisplayName()) + ChatManager.getPlayerEXP(player4));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "5. " + ChatManager.getLevelPrestigeText(player5) + RankColor.getNameColor(player5) + ChatColor.stripColor(player5.getDisplayName()) + ChatManager.getPlayerEXP(player5));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "6. " + ChatManager.getLevelPrestigeText(player6) + RankColor.getNameColor(player6) + ChatColor.stripColor(player6.getDisplayName()) + ChatManager.getPlayerEXP(player6));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "7. " + ChatManager.getLevelPrestigeText(player7) + RankColor.getNameColor(player7) + ChatColor.stripColor(player7.getDisplayName()) + ChatManager.getPlayerEXP(player7));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "8. " + ChatManager.getLevelPrestigeText(player8) + RankColor.getNameColor(player8) + ChatColor.stripColor(player8.getDisplayName()) + ChatManager.getPlayerEXP(player8));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "9. " + ChatManager.getLevelPrestigeText(player9) + RankColor.getNameColor(player9) + ChatColor.stripColor(player9.getDisplayName()) + ChatManager.getPlayerEXP(player9));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "10. " + ChatManager.getLevelPrestigeText(player10) + RankColor.getNameColor(player10) + ChatColor.stripColor(player10.getDisplayName()) + ChatManager.getPlayerEXP(player10));
        hologram3.appendTextLine("");

    }

    public static void RefreshBoard(){
        hologram.delete();
        if(hologram.isDeleted()){
            hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("world")));
        }

        hologram2.delete();
        if(hologram2.isDeleted()){
            hologram2 = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("lobby")));
        }

        hologram3.delete();
        if(hologram3.isDeleted()){
            hologram3 = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("lobby2")));
        }

        TopPlayers();
    }

    public static void delBoard(){
        if(!hologram.isDeleted()){
            hologram.delete();
        }

        if(!hologram2.isDeleted()){
            hologram2.delete();
        }

        if(!hologram3.isDeleted()){
            hologram3.delete();
        }
    }

/*    public static String getName(String id) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(id);
        if(player == null) return null;
        return player.getName();
    }

 */


    public static String getName(String uuid) {
        String url = "https://api.mojang.com/user/profile/" + uuid;
        try{
            String nameJson = IOUtils.toString(new URL(url));

            String[] list = nameJson.split(":");

            return list[2].replaceAll("\"", "").replaceAll("}", "");
        }catch (Exception ignored){
            return "ERROR";
        }
    }

    public static List<Map.Entry<String, Integer>> mainGetTop() {
        return findGreatest(ClassInstances.prestigeData.getHashMap(), 10);
    }

    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> findGreatest(HashMap<Object, Object> map, int n) {
        Comparator<? super Map.Entry<K, V>> comparator = (e0, e1) -> {
            V v0 = (V) e0.getValue();
            V v1 = (V) e1.getValue();
            return v0.compareTo(v1);
        };
        PriorityQueue<Map.Entry<K, V>> highest = new PriorityQueue(n, comparator);
        Iterator var4 = map.entrySet().iterator();

        while(var4.hasNext()) {
            Map.Entry<K, V> entry = (Map.Entry)var4.next();
            highest.offer(entry);

            while(highest.size() > n) {
                highest.poll();
            }
        }

        List<Map.Entry<K, V>> result = new ArrayList();

        while(highest.size() > 0) {
            result.add(highest.poll());
        }

        return result;
    }

}
