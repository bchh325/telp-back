package com.example.telpback.models;

import java.util.List;

public class User {

    private List<Object> liked_places;
    private List<Object> uploaded_pictures;
    private List<Object> visited_places;

    public List<Object> getLiked_places() {
        return liked_places;
    }

    public void setLiked_places(List<Object> liked_places) {
        this.liked_places = liked_places;
    }

    public List<Object> getUploaded_pictures() {
        return uploaded_pictures;
    }

    public void setUploaded_pictures(List<Object> uploaded_pictures) {
        this.uploaded_pictures = uploaded_pictures;
    }

    public List<Object> getVisited_places() {
        return visited_places;
    }

    public void setVisited_places(List<Object> visited_places) {
        this.visited_places = visited_places;
    }
}
