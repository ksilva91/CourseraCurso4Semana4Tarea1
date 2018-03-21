package com.example.kruger.petagram.restApi.model;


import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.User;

import java.util.ArrayList;


public class MediaResponse {

    ArrayList<Media> mediaArrayList;
    User user;

    public ArrayList<Media> getMediaArrayList() {
        return mediaArrayList;
    }

    public void setMediaArrayList(ArrayList<Media> mediaArrayList) {
        this.mediaArrayList = mediaArrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
