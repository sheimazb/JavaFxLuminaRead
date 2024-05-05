package com.melocode.lread;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/Package.fxml")));
        Scene scene =new Scene(parent);
        primaryStage.setTitle("Lumina Read");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
launch();
    }
}
