package com.melocode.lread;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    static String user = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost/lumina?serverTimezone=Europe/Paris";

    static String driver = "com.mysql.cj.jdbc.Driver";

    public DBConnection() {
    }

    public static Connection getCon() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection succeeded.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Connection failed.");
        }
        return connection;
    }


}
