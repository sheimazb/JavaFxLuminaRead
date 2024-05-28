package com.melocode.lread;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.melocode.lread.models.Pack;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class APICaller extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/")));
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Pack List");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static List<Pack> fetchPacksFromAPI() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://127.0.0.1:8000/api/AllPack").asJson();

            // Print the response status code
            int status = apiResponse.getStatus();
            System.out.println("Response Status Code: " + status);

            // Check the status code of the response
            if (status != 200) {
                throw new RuntimeException("Failed to fetch packs from API. HTTP error code: " + status);
            }

            JsonNode body = apiResponse.getBody();

            // Print the response body
            System.out.println("Response Body: " + body.toString());

            // Convert the response body to a Gson JsonObject
            JsonElement jsonElement = new Gson().fromJson(body.toString(), JsonElement.class);
            if (!jsonElement.isJsonObject()) {
                throw new RuntimeException("Invalid API response. Expected a JSON object.");
            }

            // Extract the "packs" array from the JSON object
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonElement packsNode = jsonObject.get("packs");

            // Check if the "packs" node is an array
            if (packsNode == null || !packsNode.isJsonArray()) {
                throw new RuntimeException("Invalid API response. Expected a JSON array.");
            }

            // Convert the "packs" array to a list of Java objects
            Type listType = new TypeToken<List<Pack>>() {}.getType();
            return new Gson().fromJson(packsNode.getAsJsonArray(), listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or throw a custom exception if necessary
        }
    }
}
