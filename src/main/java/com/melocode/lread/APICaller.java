package com.melocode.lread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.melocode.lread.models.Pack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class APICaller {
    private final String userId;
    private final String title;
    private final Integer id;
    private final boolean completed;

    public APICaller(String userId, String title, Integer id, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.id = id;
        this.completed = completed;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public static void main(String[] args) {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://127.0.0.1:8000/api/AllPack").asJson();
            String responseJsonAsString = apiResponse.getBody().toString();

            // Convertir la réponse JSON en une liste d'objets Java
            Type listType = new TypeToken<List<Pack>>(){}.getType();
            List<Pack> packList = new Gson().fromJson(responseJsonAsString, listType);

            // Parcourir la liste d'objets
            for (Pack pack : packList) {
                System.out.println("Image: " + pack.getImg());
                System.out.println("description: " + pack.getDescription());
                // Faites de même pour les autres propriétés que vous souhaitez récupérer
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
