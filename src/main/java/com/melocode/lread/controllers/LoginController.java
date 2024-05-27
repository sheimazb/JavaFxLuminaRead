package com.melocode.lread.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_password;

    @FXML
    public void loginUser(ActionEvent event) {
        String email = tf_email.getText();
        String password = tf_password.getText();

        // Validate the email and password with the server
        // You need to implement this part to connect to your server and validate the user

        if (isValidUser(email, password)) {
            // Redirect to the main application window or dashboard
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/Main.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) tf_email.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email or password");
            alert.showAndWait();
        }
    }

    private boolean isValidUser(String email, String password) {
        // Implement this method to validate the user credentials with the server
        // For example, you could use HTTP requests to send the email and password to the server
        // and check the response to see if the login is successful
        return true; // This should be replaced with actual validation logic
    }




    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Home.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/SignUp.fxml"));
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
}
