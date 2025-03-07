package com.example.telpback.dto;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;

public class ActivityDTO {
        public ActivityDTO(String userId, String placeId) {
            this.userId = userId;
            this.placeId = placeId;
        }

        private String placeId;

        private String userId;

        @ServerTimestamp
        private Timestamp timestamp;

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

}
