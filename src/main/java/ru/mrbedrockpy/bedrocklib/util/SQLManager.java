package ru.mrbedrockpy.bedrocklib.util;

import ru.mrbedrockpy.bedrocklib.BedrockLib;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SQLManager {

    private static final String FILE_PATH = BedrockLib.getInstance().getDataFolder() + "";

    private static Connection connection;

    public static void connect() {
        try {
            File dir = new File(FILE_PATH);
            if (!dir.exists()) dir.mkdirs();
            connection = DriverManager.getConnection("jdbc:sqlite:" + FILE_PATH);
            Statement statement = connection.createStatement();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Integer> loadCurrency(String id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS `" + id + "` (player TEXT NOT NULL PRIMARY KEY, balance INTEGER NOT NULL);)");
            ResultSet result = statement.executeQuery("SELECT * FROM `" + id + "`");
            Map<String, Integer> map = new HashMap<>();
            while (result.next()) map.put(result.getString("player"), result.getInt("balance"));
            statement.close();
            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
