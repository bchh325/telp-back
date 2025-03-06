package com.example.telpback.models;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import jakarta.validation.constraints.NotBlank;

public class Follower {

    public Follower(String followerId, String followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    @NotBlank

    private final String followerId;
    @NotBlank
    private final String followeeId;
    @ServerTimestamp
    private Timestamp timestamp;

    public String getFollowerId() {
        return followerId;
    }


    public String getFolloweeId() {
        return followeeId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
