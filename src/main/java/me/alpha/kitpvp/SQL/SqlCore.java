package me.alpha.kitpvp.SQL;

import java.sql.SQLException;

import java.sql.*;
import java.util.HashMap;

public class SqlCore {
    String connectionUrl;
    String username;
    String password;

    public SqlCore(String connectionUrl,
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
        String sql = "CREATE TABLE IF NOT EXISTS player_data (uuid varchar(36) primary key, playerdata TEXT(45000) , serverID varchar(36))";

        statement.execute(sql);

        statement.close();

    }

    public String findPlayerDataByUUID(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_data WHERE uuid = ?");
        statement.setString(1, uuid);

        ResultSet resultSet = statement.executeQuery();
        String toReturn = null;

        while (resultSet.next()){

            toReturn = resultSet.getString("playerdata");
        }

        statement.close();

        return toReturn;
    }

    public void createPlayerData(String uuid, String playerData, String serverID) throws SQLException {

        PreparedStatement statement = getConnection()
                .prepareStatement("INSERT INTO player_data(uuid, playerdata, serverID) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE playerdata = ?, serverID = ?");
        statement.setString(1, uuid);
        statement.setString(2, playerData);
        statement.setString(3, serverID);
        statement.setString(4, playerData);
        statement.setString(5, serverID);

        statement.executeUpdate();

        statement.close();

    }

    public void updatePlayerStats(String uuid, String playerData, String serverID) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("UPDATE player_data SET playerdata = ?, serverID = ? WHERE uuid = ?");
        statement.setString(1, playerData);
        statement.setString(2, serverID);
        statement.setString(3, uuid);

        statement.executeUpdate();

        statement.close();

    }

    public HashMap<String, String> findAllPlayerData() throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM player_data");

        ResultSet resultSet = statement.executeQuery();
        String playerData;
        String uuid = "";

        HashMap<String, String> hashMap = new HashMap<>();

        while (resultSet.next()){
            playerData=resultSet.getString("serverID");

            hashMap.put(resultSet.getString("uuid"), resultSet.getString("playerdata"));
        }

        statement.close();

        return hashMap;
    }

    public void importAllFromHashMap(HashMap<String, String> hashMap) throws SQLException {
        for(String string : hashMap.keySet()){
            createPlayerData(string, hashMap.get(string), "admin");
        }
    }

    public void deletePlayerData(String uuid) throws SQLException {

        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM player_data WHERE uuid = ?");
        statement.setString(1, uuid);

        statement.executeUpdate();

        statement.close();

    }
}
