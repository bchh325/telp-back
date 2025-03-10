package com.example.telpback.dto;

import com.example.telpback.interfaces.Media;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import jakarta.validation.constraints.NotBlank;

public class PictureDTO {
    public PictureDTO() {}

    @NotBlank
    private String userId;
    @NotBlank
    private String placeId;
    @NotBlank
    private String visibility;
    @NotBlank
    private String pictureType;

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

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    @Override
    public String toString() {
        return "PictureDTO{" +
                "userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pictureType='" + pictureType + '\'' +
                '}';
    }
}
