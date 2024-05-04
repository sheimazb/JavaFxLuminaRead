package com.melocode.lread;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageController {
    public void fetchDataFromDatabase() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String query = "SELECT title, description, category, img, langue, price FROM pack";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            // Parcourir les résultats et faire quelque chose avec eux
            while (rs.next()) {
                // Récupérer les valeurs de chaque colonne dans la ligne actuelle
                String title = rs.getString("title");
                String description = rs.getString("description");
                String category = rs.getString("category");
                String img = rs.getString("img");
                String langue = rs.getString("langue");
                double price = rs.getDouble("price");

                // Faites quelque chose avec les valeurs récupérées, comme les afficher dans la console ou les utiliser pour remplir des objets Java
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("Category: " + category);
                System.out.println("Image: " + img);
                System.out.println("Langue: " + langue);
                System.out.println("Price: " + price);
                System.out.println("-----------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermez les ressources JDBC dans le bloc finally pour éviter les fuites de ressources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



