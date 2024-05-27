package com.melocode.lread;

import com.google.gson.Gson;
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
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/market.fxml")));
        Scene scene =new Scene(parent);
        primaryStage.setTitle("Pack List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static List<Pack> fetchPacksFromAPI() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://127.0.0.1:8000/api/AllPack").asJson();
            String responseJsonAsString = apiResponse.getBody().toString();

            // Convert JSON response to a list of Java objects
            Type listType = new TypeToken<List<Pack>>(){}.getType();
            return new Gson().fromJson(responseJsonAsString, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Or throw a custom exception if necessary
        }
    }
}
