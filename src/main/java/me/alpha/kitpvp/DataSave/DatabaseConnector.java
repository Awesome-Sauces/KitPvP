package me.alpha.kitpvp.DataSave;

import me.alpha.kitpvp.Data.ClassInstances;
import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.SQL.PrestigeSqlCore;
import me.alpha.kitpvp.SQL.SqlCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseConnector {
    public static SqlCore database;
    public static PrestigeSqlCore prestigeDatabase;

    public static void startConnection(){
        database = new SqlCore("jdbc:mysql://212.192.28.120:3306/s86215_playerdata",
                "u86215_jcoiTWvj6N",
                "Syhm^xKSj.Ms+HI!.9Phvy.h");

        try {
            database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

        KitPvP.INSTANCE.getServer().getScheduler().runTaskTimer(KitPvP.INSTANCE, () -> {
            try {
                database.reconnect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        },0,12000);


        prestigeDatabase = new PrestigeSqlCore("jdbc:mysql://212.192.28.120:3306/s86215_prestiges",
                "u86215_4i9Qo9lLi9",
                "OtQbPR^1RfqK1wx4klZ^@cDH");

        try {
            prestigeDatabase.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

        KitPvP.INSTANCE.getServer().getScheduler().runTaskTimer(KitPvP.INSTANCE, () -> {
            try {
                prestigeDatabase.reconnect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        },0,15000);

    }

    public static void updatePlayer(String uuid, String playerData, String serverID) throws SQLException {

        //database.deletePlayerData(uuid);
        database.createPlayerData(uuid, playerData, serverID);
    }

    public static String getPlayer(String uuid) throws SQLException {

        return database.findPlayerDataByUUID(uuid);
    }

    public static void loadPlayer(Player player){
        String playerData = "";

        try {
            playerData = DatabaseConnector.getPlayer(player.getUniqueId().toString());

            if(playerData==null){
                player.sendMessage(ColorUtil.colorCode("&cFailed to deserialize player data"));
                System.out.println("PLAYER DATA NULL=FAILED TO DESERIALIZE PLAYER DATA");
                Sounds.ERROR.play(player);
                return;
            }

            Converter64.playerDataFrom64(playerData).loadData(player);
            player.sendMessage(ColorUtil.colorCode("&aSuccessfully deserialized and loaded player data"));
            Sounds.SUCCESS.play(player);
        } catch (SQLException | IOException e) {
            player.sendMessage(ColorUtil.colorCode("&cFailed to deserialize player data"));
            Sounds.ERROR.play(player);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void savePlayer(Player player){

        PlayerData playerData = new PlayerData(player.getUniqueId().toString());

        playerData.saveData(player);

        try {
            DatabaseConnector.updatePlayer(player.getUniqueId().toString(), Converter64.playerDataTo64(playerData), player.getServer().getName());
            player.sendMessage(ColorUtil.colorCode("&aSuccessfully saved and serialized player data"));
            Sounds.SUCCESS.play(player);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void updatePrestige(Player player) throws SQLException {
        prestigeDatabase.createPlayerData(player.getUniqueId().toString(), ClassInstances.prestigeData.getPrestige(player.getUniqueId().toString()));
    }

    public static void updatePrestige(String uuid, int prestige) throws SQLException {
        prestigeDatabase.createPlayerData(uuid, prestige);
    }

    public static HashMap<String, Integer> getHashMapPrestige() throws SQLException {
        HashMap<String, Integer> hashMap = prestigeDatabase.findAllPrestige();

        if(hashMap.size()<10){
            hashMap.put("c787373d-1b2d-4426-a68d-4be36e76ccaf", 0);
            hashMap.put("15976ee6-44ee-4837-a248-3bd628a37e14", 0);
            hashMap.put("14dbf2e7-73ef-4d28-a96b-74b93c7d27c9", 0);
            hashMap.put("6b5031ec-8052-4c46-8b26-fa51c856f89c", 0);
            hashMap.put("8ca20386-4314-4157-926c-0b947860b5af", 0);
            hashMap.put("ee3ff035-882e-49ad-b8d3-ea7403426a88", 0);
            hashMap.put("f5a395e8-976e-4aeb-87a7-b1b42057b617", 0);
            hashMap.put("32c2f7c0-567d-40c9-a42e-03098c933e8e", 0);
            hashMap.put("9c4819e9-7d42-4cb9-8b5a-7f9cb22ce198", 0);
            hashMap.put("aca28821-7738-4001-9bdf-394b8ba74b9c", 0);
        }
        return hashMap;
    }
}
