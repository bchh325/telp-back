package com.example.telpback.models;

import com.example.telpback.dto.VideoDTO;
import com.example.telpback.interfaces.Media;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;

public class Video implements Media {
    private String uploadType;
    private String userId;
    private String placeId;
    private String visibility;

    @ServerTimestamp
    private Timestamp timestamp;

    public Video() {
    }

    public Video(VideoDTO metadata) {
        this.uploadType = "image";
        this.placeId = metadata.getPlaceId();
        this.userId = metadata.getUserId();
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String getPictureType() {
        return null;
    }

    @Override
    public String toString() {
        return "Video{" +
                "uploadType='" + uploadType + '\'' +
                ", userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", visibility='" + visibility + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
