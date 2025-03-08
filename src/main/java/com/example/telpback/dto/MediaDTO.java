package com.example.telpback.dto;

public class MediaDTO {
    public MediaDTO() {}

    private String userId;
    private String mimeType;
    private String placeId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "MediaDTO{" +
                "userId='" + userId + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", placeId='" + placeId + '\'' +
                '}';
    }
}
