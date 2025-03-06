package com.example.telpback.models;

import com.example.telpback.validators.UserCreationConstraints;
import com.example.telpback.validators.UserUpdateConstraints;
import com.google.cloud.firestore.annotation.Exclude;
import jakarta.validation.constraints.NotBlank;

public class User {
    public User(String userId, String username, String visibility) {
        this.userId = userId;
        this.username = username;
        this.visibility = visibility;
    }

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User() {}


    @NotBlank(
            message = "UserID cannot be blank.",
            groups = {UserCreationConstraints.class, UserUpdateConstraints.class}
    )
    private String userId;

    @NotBlank(
            message = "Username cannot be blank",
            groups = {UserCreationConstraints.class}
    )
    private String username;
    private String visibility;

    @Exclude
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", visibility='" + visibility + '\'' +
                '}';
    }
}
