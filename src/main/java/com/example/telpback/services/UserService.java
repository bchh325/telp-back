package com.example.telpback.services;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.User;

public class UserService extends BaseFirestoreService<User> {
    public UserService() {
        super("users", User.class);
    }

    public void setNewUser(User user) {
        String userId = user.getUserId();
        if (!super.documentExists(userId)) {
            DocumentDTO<User> document = new DocumentDTO<>(userId, user);
            super.setDocument(document);
        }
    }

    public void updateUserFields(User userFields) {

    }
}
