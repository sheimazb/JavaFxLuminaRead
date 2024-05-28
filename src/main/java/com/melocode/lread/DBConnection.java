package com.melocode.lread;

import com.melocode.lread.models.Pack;
import com.melocode.lread.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost/lumina?serverTimezone=Europe/Paris&useSSL=false"; // Disable SSL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public DBConnection() {}

    public static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection succeeded.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection failed.");
        }
        return connection;
    }

    public User getUserProfile(int userId) {
        User user = null;
        String sql = "SELECT img, name, description FROM users WHERE id = ?";

        try (Connection connection = DBConnection.getCon();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                user = new User(img, name, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<Pack> getPacksForUser(int userId) {
        List<Pack> packs = new ArrayList<>();
        String sql = "SELECT * FROM packs WHERE user_id = ?";

        try (Connection connection = DBConnection.getCon();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String img = resultSet.getString("img");
                String updated_at = resultSet.getString("updated_at");
                int user_id = resultSet.getInt("user_id");
                String price = resultSet.getString("price");
                String description = resultSet.getString("description");
                String created_at = resultSet.getString("created_at");
                int packStatus = resultSet.getInt("packStatus");
                int id = resultSet.getInt("id");
                String langue = resultSet.getString("langue");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");

                Pack pack = new Pack(img, updated_at, null, user_id, price, description, created_at, packStatus, id, langue, title, category);
                packs.add(pack);

                // Debug print to check the retrieved pack data
                System.out.println("Pack retrieved: " + title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packs;
    }

}
