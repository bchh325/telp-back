package com.example.telpback.models;

import com.example.telpback.validators.ActivityConstraints;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Map;

@ActivityConstraints
public class Activity {

    public Activity() {}

    private String userId;

    private ArrayList<LikedPlace> likedPlaces;
    private ArrayList<VisitedPlace> visitedPlaces;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<LikedPlace> getLikedPlaces() {
        return likedPlaces;
    }

    public void setLikedPlaces(ArrayList<LikedPlace> likedPlaces) {
        this.likedPlaces = likedPlaces;
    }

    public ArrayList<VisitedPlace> getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(ArrayList<VisitedPlace> visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }

    public static class LikedPlace {
        public LikedPlace() {}

        private String placeId;

        @ServerTimestamp
        private Timestamp timestamp;

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

        @Override
        public String toString() {
            return "LikedPlace{" +
                    "placeId='" + placeId + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class VisitedPlace {
        public VisitedPlace() {}

        private String placeId;
        @ServerTimestamp
        private Timestamp timestamp;

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

        @Override
        public String toString() {
            return "VisitedPlace{" +
                    "placeId='" + placeId + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Activity{" +
                "userId='" + userId + '\'' +
                ", likedPlaces=" + likedPlaces +
                ", visitedPlaces=" + visitedPlaces +
                '}';
    }
}
