package com.melocode.lread;

import javafx.beans.property.*;

public class Package {
    private final IntegerProperty id;
    private final IntegerProperty userId;
    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty category;
    private final StringProperty price;
    private final StringProperty langue;

    public Package(int id, int userId, String title, String description, String category, String price, String langue) {
        this.id = new SimpleIntegerProperty(id);
        this.userId = new SimpleIntegerProperty(userId);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.price = new SimpleStringProperty(price);
        this.langue = new SimpleStringProperty(langue);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getUserId() {
        return userId.get();
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getLangue() {
        return langue.get();
    }

    public StringProperty langueProperty() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue.set(langue);
    }
}
