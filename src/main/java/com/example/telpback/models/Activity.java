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

    private Map<String, LikedPlace> likedPlaces;
    private ArrayList<VisitedPlace> visitedPlaces;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, LikedPlace> getLikedPlaces() {
        return likedPlaces;
    }

    public void setLikedPlaces(Map<String, LikedPlace> likedPlaces) {
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

        @ServerTimestamp
        private Timestamp timestamp;

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "LikedPlace{" +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class VisitedPlace {
        public VisitedPlace() {}

        @ServerTimestamp
        private Timestamp timestamp;

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "VisitedPlace{" +
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
