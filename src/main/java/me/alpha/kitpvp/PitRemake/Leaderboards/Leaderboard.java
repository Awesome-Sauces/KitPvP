package me.alpha.kitpvp.PitRemake.Leaderboards;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.alpha.kitpvp.ChatManager.ChatManager;
import me.alpha.kitpvp.DataSave.DatabaseConnector;
import me.alpha.kitpvp.KitPvP;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

import static me.alpha.kitpvp.PitRemake.Locations.getLeaderBoardLocation;
import static me.alpha.kitpvp.utils.ColorUtil.colorCode;

public class Leaderboard {

    public static HashMap<Integer, ArmorStand> LeaderBoard = new HashMap<>();

    private static Hologram hologram = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("world")));
    private static Hologram hologram2 = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("lobby")));
    private static Hologram hologram3 = HologramsAPI.createHologram(KitPvP.INSTANCE, getLeaderBoardLocation(Bukkit.getWorld("lobby2")));

    public static void TopPlayers() throws SQLException {

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

            if (topPlayers.get(9).getKey() != null){
                top1 = getName(topPlayers.get(9).getKey());
            }

            if (topPlayers.get(8).getKey() != null){
                top2 = getName(topPlayers.get(8).getKey());
            }

            if (topPlayers.get(7).getKey() != null){
                top3 = getName(topPlayers.get(7).getKey());
            }

            if (topPlayers.get(6).getKey() != null){
                top4 = getName(topPlayers.get(6).getKey());
            }

            if (topPlayers.get(5).getKey() != null){
                top5 = getName(topPlayers.get(5).getKey());
            }

            if (topPlayers.get(4).getKey() != null){
                top6 = getName(topPlayers.get(4).getKey());
            }

            if (topPlayers.get(3).getKey() != null){
                top7 = getName(topPlayers.get(3).getKey());
            }

            if (topPlayers.get(2).getKey() != null){
                top8 = getName(topPlayers.get(2).getKey());
            }

            if (topPlayers.get(1).getKey() != null){
                top9 = getName(topPlayers.get(1).getKey());
            }

            if (topPlayers.get(0).getKey() != null){
                top10 = getName(topPlayers.get(0).getKey());
            }


            //For every player, add their name to gui
            hologram.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "1. " + ChatManager.getLevelPrestigeText(topPlayers.get(9).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top1) + ChatManager.getPlayerEXP(topPlayers.get(9).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "2. " + ChatManager.getLevelPrestigeText(topPlayers.get(8).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top2) + ChatManager.getPlayerEXP(topPlayers.get(8).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "3. " + ChatManager.getLevelPrestigeText(topPlayers.get(7).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top3) + ChatManager.getPlayerEXP(topPlayers.get(7).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "4. " + ChatManager.getLevelPrestigeText(topPlayers.get(6).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top4) + ChatManager.getPlayerEXP(topPlayers.get(6).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "5. " + ChatManager.getLevelPrestigeText(topPlayers.get(5).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top5) + ChatManager.getPlayerEXP(topPlayers.get(5).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "6. " + ChatManager.getLevelPrestigeText(topPlayers.get(4).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top6) + ChatManager.getPlayerEXP(topPlayers.get(4).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "7. " + ChatManager.getLevelPrestigeText(topPlayers.get(3).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top7) + ChatManager.getPlayerEXP(topPlayers.get(3).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "8. " + ChatManager.getLevelPrestigeText(topPlayers.get(2).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top8) + ChatManager.getPlayerEXP(topPlayers.get(2).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "9. " + ChatManager.getLevelPrestigeText(topPlayers.get(1).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top9) + ChatManager.getPlayerEXP(topPlayers.get(1).getKey()));
            hologram.appendTextLine("");
            hologram.appendTextLine(ChatColor.YELLOW + "10. " + ChatManager.getLevelPrestigeText(topPlayers.get(0).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top10) + ChatManager.getPlayerEXP(topPlayers.get(0).getKey()));
            hologram.appendTextLine("");
        hologram.appendTextLine(colorCode("&7All-time &ebest &7players!"));
        hologram.appendTextLine(colorCode("&7&oPlayers who logged in this week"));

        //For every player, add their name to gui
            hologram2.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
            hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "1. " + ChatManager.getLevelPrestigeText(topPlayers.get(9).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top1) + ChatManager.getPlayerEXP(topPlayers.get(9).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "2. " + ChatManager.getLevelPrestigeText(topPlayers.get(8).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top2) + ChatManager.getPlayerEXP(topPlayers.get(8).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "3. " + ChatManager.getLevelPrestigeText(topPlayers.get(7).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top3) + ChatManager.getPlayerEXP(topPlayers.get(7).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "4. " + ChatManager.getLevelPrestigeText(topPlayers.get(6).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top4) + ChatManager.getPlayerEXP(topPlayers.get(6).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "5. " + ChatManager.getLevelPrestigeText(topPlayers.get(5).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top5) + ChatManager.getPlayerEXP(topPlayers.get(5).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "6. " + ChatManager.getLevelPrestigeText(topPlayers.get(4).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top6) + ChatManager.getPlayerEXP(topPlayers.get(4).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "7. " + ChatManager.getLevelPrestigeText(topPlayers.get(3).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top7) + ChatManager.getPlayerEXP(topPlayers.get(3).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "8. " + ChatManager.getLevelPrestigeText(topPlayers.get(2).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top8) + ChatManager.getPlayerEXP(topPlayers.get(2).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "9. " + ChatManager.getLevelPrestigeText(topPlayers.get(1).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top9) + ChatManager.getPlayerEXP(topPlayers.get(1).getKey()));
        hologram2.appendTextLine("");
        hologram2.appendTextLine(ChatColor.YELLOW + "10. " + ChatManager.getLevelPrestigeText(topPlayers.get(0).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top10) + ChatManager.getPlayerEXP(topPlayers.get(0).getKey()));
            hologram2.appendTextLine("");
        hologram2.appendTextLine(colorCode("&7All-time &ebest &7players!"));
        hologram2.appendTextLine(colorCode("&7&oPlayers who logged in this week"));

        //For every player, add their name to gui
        hologram3.appendTextLine(ChatColor.AQUA + ChatColor.translateAlternateColorCodes('&', "&lTOP ACTIVE PLAYERS"));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "1. " + ChatManager.getLevelPrestigeText(topPlayers.get(9).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top1) + ChatManager.getPlayerEXP(topPlayers.get(9).getKey()) );
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "2. " + ChatManager.getLevelPrestigeText(topPlayers.get(8).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top2) + ChatManager.getPlayerEXP(topPlayers.get(8).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "3. " + ChatManager.getLevelPrestigeText(topPlayers.get(7).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top3) + ChatManager.getPlayerEXP(topPlayers.get(7).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "4. " + ChatManager.getLevelPrestigeText(topPlayers.get(6).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top4) + ChatManager.getPlayerEXP(topPlayers.get(6).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "5. " + ChatManager.getLevelPrestigeText(topPlayers.get(5).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top5) + ChatManager.getPlayerEXP(topPlayers.get(5).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "6. " + ChatManager.getLevelPrestigeText(topPlayers.get(4).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top6) + ChatManager.getPlayerEXP(topPlayers.get(4).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "7. " + ChatManager.getLevelPrestigeText(topPlayers.get(3).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top7) + ChatManager.getPlayerEXP(topPlayers.get(3).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "8. " + ChatManager.getLevelPrestigeText(topPlayers.get(2).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top8) + ChatManager.getPlayerEXP(topPlayers.get(2).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "9. " + ChatManager.getLevelPrestigeText(topPlayers.get(1).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top9) + ChatManager.getPlayerEXP(topPlayers.get(1).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(ChatColor.YELLOW + "10. " + ChatManager.getLevelPrestigeText(topPlayers.get(0).getKey()) + ChatColor.GRAY + ChatColor.stripColor(top10) + ChatManager.getPlayerEXP(topPlayers.get(0).getKey()));
        hologram3.appendTextLine("");
        hologram3.appendTextLine(colorCode("&7All-time &ebest &7players!"));
        hologram3.appendTextLine(colorCode("&7&oPlayers who logged in this week"));

    }

    public static void RefreshBoard() throws SQLException {
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

    public static List<Map.Entry<String, Integer>> mainGetTop() throws SQLException {
        return findGreatest(DatabaseConnector.getHashMapPrestige(), 10);
    }

    private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> findGreatest(HashMap<String, Integer> map, int n) {
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
