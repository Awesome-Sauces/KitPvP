package me.alpha.kitpvp.DataSave;

import me.alpha.kitpvp.SQL.SqlCore;

import java.sql.SQLException;

public class DatabaseConnector {
    public static SqlCore database;

    public static void startConnection(){
        database = new SqlCore("jdbc:mysql://212.192.28.145:3306/s1_playerdata",
                "u1_9oOYGKYC2f",
                "bdmx9dQ@iMOIRdugSMC6+6eB");

        try {
            database.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not initialize database.");
        }

    }

    public static void updatePlayer(String uuid, String playerData, String serverID) throws SQLException {
        database.deletePlayerData(uuid);
        database.createPlayerData(uuid, playerData, serverID);
    }

    public static String getPlayer(String uuid) throws SQLException {
        return database.findPlayerDataByUUID(uuid);
    }
}
