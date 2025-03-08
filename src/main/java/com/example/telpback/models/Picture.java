package com.example.telpback.models;

import com.example.telpback.dto.MediaDTO;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import org.springframework.web.multipart.MultipartFile;

public class Picture {
    private String userId;
    private String mimeType;
    private String placeId;

    @ServerTimestamp
    private Timestamp timestamp;

    public Picture() {
    }

    public Picture(MediaDTO mediaDto) {
        this.placeId = placeId;
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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "placeId='" + placeId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
