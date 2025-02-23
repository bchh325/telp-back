package com.example.telpback.models;

import java.util.ArrayList;

public class User {
    public User(String userId) {
        this.userId = userId;
        this.likedPlaces = new ArrayList<>();
        this.uploadedPictures = new ArrayList<>();
        this.visitedPlaces = new ArrayList<>();
    }

    private String userId;

    private ArrayList<String> likedPlaces;
    private ArrayList<String> uploadedPictures;
    private ArrayList<String> visitedPlaces;

    public ArrayList<String> getLikedPlaces() {
        return likedPlaces;
    }

    public void setLikedPlaces(ArrayList<String> likedPlaces) {
        this.likedPlaces = likedPlaces;
    }

    public ArrayList<String> getUploadedPictures() {
        return uploadedPictures;
    }

    public void setUploadedPictures(ArrayList<String> uploadedPictures) {
        this.uploadedPictures = uploadedPictures;
    }

    public ArrayList<String> getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(ArrayList<String> visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }
}
