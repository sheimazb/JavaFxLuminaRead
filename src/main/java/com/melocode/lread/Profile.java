package com.melocode.lread;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Profile extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Profile.fxml"));
            Scene scene = new Scene(root, 600, 600);
         scene.getStylesheets().add(getClass().getResource("/Fxml/style/global.css").toExternalForm());
            primaryStage.setTitle("User Profile");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
