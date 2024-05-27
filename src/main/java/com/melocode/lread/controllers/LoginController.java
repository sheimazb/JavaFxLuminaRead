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
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

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
    private Button btn_login;

    @FXML
    public void loginUser(ActionEvent event) {
        String email = tf_email.getText();
        String password = tf_password.getText();

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);
            jsonBody.put("password", password);

            URL url = new URL("http://127.0.0.1:8000/api/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(jsonBody.toString().getBytes());
            os.flush();
            os.close();
            goToHome();
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                goToHome();
            } else {
                showAlert(AlertType.ERROR, "Error", null, "HTTP error code: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", null, "An error occurred while processing your request: " + e.getMessage());
        }
    }
    private void goToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/HomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tf_email.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR,"Error",null, "An error occurred while trying to load the login page: " + e.getMessage());
        }
    }

    private void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Home.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/SignUp.fxml"));
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
