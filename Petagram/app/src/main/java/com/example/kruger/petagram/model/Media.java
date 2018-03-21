package com.example.kruger.petagram.model;

/**
 * Created by Kruger on 25/1/2018.
 */

public class Media {
    private String id;
    private String name;
    private String urlPhoto;
    private int likes = 0;

    public Media() {
    }

    public Media(String id, String name, String urlPhoto, int likes) {
        this.id = id;
        this.name = name;
        this.urlPhoto = urlPhoto;
        this.likes = likes;
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

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
