package com.example.telpback.models;

import com.example.telpback.dto.PictureDTO;
import com.example.telpback.interfaces.Media;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class Picture implements Media {

    @Exclude
    private String uuid;
    private String uploadType;
    private String userId;
    private String placeId;
    private String visibility;
    private String pictureType;

    @ServerTimestamp
    private Timestamp timestamp;

    public Picture() {
    }

    public Picture(PictureDTO metadata) {
        if (metadata.getPictureType().equals("general")) {
            this.uuid = UUID.randomUUID().toString();
            this.placeId = metadata.getPlaceId();
            this.userId = metadata.getUserId();
            this.pictureType = metadata.getPictureType();
            this.visibility = metadata.getVisibility();
        }

        if (metadata.getPictureType().equals("profile")) {
            this.userId = metadata.getUserId();
            this.pictureType = metadata.getPictureType();
        }

        this.uploadType = "image";

    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "uploadType='" + uploadType + '\'' +
                ", userId='" + userId + '\'' +
                ", placeId='" + placeId + '\'' +
                ", visibility='" + visibility + '\'' +
                ", pictureType='" + pictureType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
