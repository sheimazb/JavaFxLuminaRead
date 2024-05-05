package com.melocode.lread;

import javafx.beans.property.*;

public class Package {
    private int id;
    private int user_id;
    private String title;
    private String description;
    private String category;
    private String price;
    private String langue;


    public Package(int id,int user_id, String title, String description, String category, String price, String langue) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.langue = langue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
