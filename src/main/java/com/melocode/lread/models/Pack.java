package com.melocode.lread.models;

public class Pack {
    private String img;
    private String updated_at;
    private User usser;
    private int user_id;
    private String price;
    private String description;
    private String created_at;
    private int packStatus;
    private int id;
    private String langue;
    private String title;
    private String category;

    public Pack(String img, String updated_at, User usser, int user_id, String price, String description, String created_at, int packStatus, int id, String langue, String title, String category) {
        this.img = img;
        this.updated_at = updated_at;
        this.usser = usser;
        this.user_id = user_id;
        this.price = price;
        this.description = description;
        this.created_at = created_at;
        this.packStatus = packStatus;
        this.id = id;
        this.langue = langue;
        this.title = title;
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public User getUsser() {
        return usser;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getPackStatus() {
        return packStatus;
    }

    public int getId() {
        return id;
    }

    public String getLangue() {
        return langue;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}
