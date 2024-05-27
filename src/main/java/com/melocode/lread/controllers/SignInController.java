package com.melocode.lread.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class SignInController {
    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_password;

    @FXML
    private PasswordField tf_comfirmpassword;

    @FXML
    public void addUser() {
        String name = tf_name.getText();
        String email = tf_email.getText();
        String password = tf_password.getText();
        String confirmPassword = tf_comfirmpassword.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost("http://127.0.0.1:8000/api/register");
            post.setHeader("Content-Type", "application/json");

            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("email", email);
            json.put("password", password);

            StringEntity entity = new StringEntity(json.toString());
            post.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseString = EntityUtils.toString(response.getEntity());

                if (statusCode == 200) {
                    JSONObject jsonResponse = new JSONObject(responseString);
                    if (jsonResponse.getBoolean("success")) {
                        showAlert("Success", "Registration successful! Please check your email for verification.");
                    } else {
                        String message = jsonResponse.optString("message", "Registration failed.");
                        showAlert("Error", message);
                    }
                } else {
                    showAlert("Error", "Server returned status code: " + statusCode + ". Response: " + responseString);
                }
            }
        } catch (IOException e) {
            showAlert("Error", "An error occurred: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
