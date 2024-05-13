package com.melocode.lread.models;

import java.util.List;

public class User {
    private String img;
    private String name;
    private String description;

    public User(String img, String name, String description) {
        this.img = img;
        this.name = name;
        this.description = description;
    }
    private List<Pack> packs;

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }
    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
