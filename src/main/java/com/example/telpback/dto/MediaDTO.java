package com.example.telpback.dto;

import com.example.telpback.interfaces.Media;

public class MediaDTO {

    public MediaDTO() {}

    private String uploadType;

    private String userId;
    private String mimeType;
    private String placeId;
    private String visibility;

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
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
