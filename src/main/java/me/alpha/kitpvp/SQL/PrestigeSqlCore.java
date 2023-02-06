package me.alpha.kitpvp.SQL;

import java.sql.SQLException;

import java.sql.*;
import java.util.HashMap;

public class PrestigeSqlCore {
    String connectionUrl;
    String username;
    String password;

    public PrestigeSqlCore(String connectionUrl,
                   String username,
                   String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if(connection != null){
            return connection;
        }

        //Try to connect to my MySQL database running locally


        Connection connection = DriverManager.getConnection(connectionUrl, username, password);

        this.connection = connection;

        System.out.println("Connected to database.");

        return connection;
    }

    public void reconnect() throws SQLException {
        connection=null;
        getConnection();
    }

    public void initializeDatabase() throws SQLException {

        Statement statement = getConnection().createStatement();

        int length = 100000;

        //Create the player_data table
        String sql = "CREATE TABLE IF NOT EXISTS prestige_data (uuid varchar(36) primary key, prestige INT)";

        statement.execute(sql);

        statement.close();

    }

    public int findPrestigeByUUID(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM prestige_data WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();
        int toReturn = 0;

        while (resultSet.next()){

            toReturn = resultSet.getInt("prestige");
        }

        statement.close();

        return toReturn;
    }

    public HashMap<String, Integer> findAllPrestige() throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM prestige_data");

        ResultSet resultSet = statement.executeQuery();
        int prestige = 0;
        String uuid = "";

        HashMap<String, Integer> hashMap = new HashMap<>();

        while (resultSet.next()){

            prestige = resultSet.getInt("prestige");
            uuid = resultSet.getString("uuid");

            hashMap.put(uuid, prestige);
        }

        statement.close();

        return hashMap;
    }

    public void createPlayerData(String uuid, int prestige) throws SQLException {

        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO prestige_data(uuid, prestige) VALUES (?, ?) ON DUPLICATE KEY UPDATE prestige = ?");
        statement.setString(1, uuid);
        statement.setInt(2, prestige);
        statement.setInt(3, prestige);

        statement.executeUpdate();

        statement.close();

    }

    public void deletePlayerData(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM prestige_data WHERE uuid = ?");
        statement.setString(1, uuid);

        statement.executeUpdate();

        statement.close();

    }
}
