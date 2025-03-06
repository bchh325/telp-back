package com.example.telpback.services;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.User;
import com.example.telpback.models.ValidationResult;
import com.google.cloud.firestore.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BaseFirestoreService<User> userService;

    public UserService(BaseFirestoreService<User> userBaseFirestoreService) {
        this.userService = userBaseFirestoreService;
    }

    public ValidationResult createUser(User user) throws Exception {
        String userId = user.getUserId();
        String username = user.getUsername();

        boolean documentAlreadyExists = userService.documentExists(userId);
        if (documentAlreadyExists) {
            return new ValidationResult(true, HttpStatus.CONFLICT, "Document with ID " + userId + " already exists as a resource.");
        }

        Query usernameExistsQuery = userService.getRef().whereEqualTo("username", username).limit(1);
        boolean usernameAlreadyExists = !userService.executeQuery(usernameExistsQuery).isEmpty();
        if (usernameAlreadyExists) {
            return new ValidationResult(true, HttpStatus.CONFLICT, "Document with username " + username + " already exists.");
        }

        if (userService.setDocument(new DocumentDTO<>(userId, user))) {
            return new ValidationResult(false, HttpStatus.CREATED, "User created successfully.");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user. Internal server error.");
        }
    }

    public ValidationResult updateUser(User user) throws Exception {
        String userId = user.getUserId();

        boolean userUpdateSuccess = userService.updateDocument(new DocumentDTO<>(userId, user));
        if (userUpdateSuccess) {
            return new ValidationResult(false, HttpStatus.OK, "Successfully updated user document.");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error updating document.");
        }
    }

    public String testFunction() {
        return "testFunction value";
    }
}
