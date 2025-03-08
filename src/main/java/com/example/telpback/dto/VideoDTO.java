package com.example.telpback.dto;

public class VideoDTO {
    public VideoDTO() {}

    private String userId;
    private String placeId;
    private String visibility;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "VideoDTO{" +
                "userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
