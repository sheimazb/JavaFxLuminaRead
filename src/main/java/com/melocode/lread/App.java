package com.melocode.lread;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try { Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/Package.fxml")));
            Scene scene =new Scene(parent);
            primaryStage.setTitle("Lumina Read");
            primaryStage.setScene(scene);
            primaryStage.show();
            parent.setOnMouseClicked(event -> {

                Parent newParent = null;
                try {
                    newParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/test.fxml")));
               System.out.println("test");
                    URL url = new URL("http://127.0.0.1:8000/api/AllPack");

                    // Create connection
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    // Get response code
                    int responseCode = connection.getResponseCode();
                    System.out.println("Response Code: " + responseCode);

                    // Read response
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Print response
                    System.out.println("Response Body: " + response.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene newScene = new Scene(newParent);
                primaryStage.setScene(newScene);


            });
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page FXML
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
