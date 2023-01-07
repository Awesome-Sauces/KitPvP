package me.alpha.kitpvp.DataSave;

import me.alpha.kitpvp.KitPvP;
import me.alpha.kitpvp.PitRemake.PitCommands.Stash.StashCore;
import me.alpha.kitpvp.SQL.SqlCore;
import me.alpha.kitpvp.utils.ColorUtil;
import me.alpha.kitpvp.utils.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConnector {
    public static SqlCore database;

    public static void startConnection(){
        database = new SqlCore("jdbc:mysql://212.192.28.145:3306/s1_playerdata",
                "u1_lepIss6QU3",
                "y0C55=9f6^nH4x.ln6agnPgJ");

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

    }

    public static void updatePlayer(String uuid, String playerData, String serverID) throws SQLException {

        database.deletePlayerData(uuid);
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
                Sounds.ERROR.play(player);
                return;
            }

            Converter64.playerDataFrom64(playerData).loadData(player);
            player.sendMessage(ColorUtil.colorCode("&aSuccessfully deserialized and loaded player data"));
            Sounds.SUCCESS.play(player);
        } catch (SQLException | IOException e) {
            player.sendMessage(ColorUtil.colorCode("&cFailed to deserialize player data"));
            Sounds.ERROR.play(player);
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
            throw new RuntimeException(e);
        }
    }
}
