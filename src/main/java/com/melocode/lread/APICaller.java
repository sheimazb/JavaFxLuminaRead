package com.melocode.lread;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
            // Create URL
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://127.0.0.1:8000/api/AllPack").asJson();
            String responseJsonAsString = apiResponse.getBody().toString();

            APICaller dogResponse = new Gson().fromJson(apiResponse.getBody().toString(), APICaller.class);

            System.out.println("Response Body: " + dogResponse.getUserId());
            System.out.println("Response Body: " + dogResponse.getTitle());
            System.out.println("Response Body: " + dogResponse.getId());
            System.out.println("Response Body: " + responseJsonAsString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
