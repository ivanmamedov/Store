package com.example.ivan.store.objects;

/**
 * Created by Ivan on 16.10.2017.
 */

public class CardCategory {
    private String id;
    private String name;
    private String image;
    private String information;

    public CardCategory(String id, String name, String image, String information) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.information = information;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
