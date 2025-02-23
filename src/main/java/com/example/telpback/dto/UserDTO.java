package com.example.telpback.dto;

import com.google.cloud.firestore.annotation.Exclude;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserDTO {
    public UserDTO() {}
    public UserDTO(ArrayList<String> likedPlaces, ArrayList<String> uploadedPictures, ArrayList<String> visitedPlaces) {
        this.likedPlaces = likedPlaces;
        this.uploadedPictures = uploadedPictures;
        this.visitedPlaces = visitedPlaces;
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

    @Exclude
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", likedPlaces=" + likedPlaces +
                ", uploadedPictures=" + uploadedPictures +
                ", visitedPlaces=" + visitedPlaces +
                '}';
    }
}
