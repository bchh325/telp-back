package com.example.telpback.models;

import com.example.telpback.validators.ActivityConstraints;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@ActivityConstraints
public class Activity {
    public interface ActivityMethods {
        String getPlaceId();
    }

    public Activity() {}

    private String userId;

    @Valid
    private List<LikedPlace> likedPlaces;
    private List<VisitedPlace> visitedPlaces;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<LikedPlace> getLikedPlaces() {
        return likedPlaces;
    }

    public void setLikedPlaces(List<LikedPlace> likedPlaces) {
        this.likedPlaces = likedPlaces;
    }

    public List<VisitedPlace> getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(List<VisitedPlace> visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
    }

    public static class LikedPlace implements ActivityMethods {

        public LikedPlace() {}

        @NotNull
        private String placeId;

        @ServerTimestamp
        private Timestamp timestamp;

        @Override
        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        @Override
        public String toString() {
            return "LikedPlace{" +
                    "placeId='" + placeId + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class VisitedPlace implements ActivityMethods {
        public VisitedPlace() {}

        @NotNull
        private String placeId;

        @ServerTimestamp
        private Timestamp timestamp;

        @Override
        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
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
