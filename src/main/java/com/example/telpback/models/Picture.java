package com.example.telpback.models;

import org.springframework.web.multipart.MultipartFile;

public class Picture {
    private String placeId;

    public Picture() {}

    public Picture(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
