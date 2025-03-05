package com.example.telpback.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.User;
import com.example.telpback.models.ValidationResult;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Query;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseFirestoreService<User> {
    public UserService() {
        super("users", User.class);
    }

    public ValidationResult createUser(User user) throws Exception {
        String userId = user.getUserId();
        String username = user.getUsername();

        Query usernameExistsQuery = super.getRef().whereEqualTo("username", username);

        boolean documentAlreadyExists = super.documentExists(userId);
        if (documentAlreadyExists) {
            return new ValidationResult(true, HttpStatus.CONFLICT, "Document with ID " + userId + " already exists as a resource.");
        }

        boolean usernameAlreadyExists = !super.executeQuery(usernameExistsQuery).isEmpty();
        if (usernameAlreadyExists) {
            return new ValidationResult(true, HttpStatus.CONFLICT, "Document with username " + username + " already exists.");
        }

        if (super.setDocument(new DocumentDTO<>(userId, user))) {
            return new ValidationResult(false, HttpStatus.CREATED, "User created successfully.");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user. Internal server error.");
        }
    }

    public ValidationResult updateUser(User user) throws Exception {
        String userId = user.getUserId();

        if (super.updateDocument(new DocumentDTO<>(userId, user))) {
            return new ValidationResult(false, HttpStatus.OK, "Successfully updated user document.");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error updating document.");
        }
    }

    public String testFunction() {
        return "testFunction value";
    }
}
