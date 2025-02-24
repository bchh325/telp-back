package com.example.telpback.services;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.dto.UserDTO;
import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.User;

public class UserService extends BaseFirestoreService<UserDTO> {
    public UserService() {
        super("users", UserDTO.class);
    }

    public void setNewUser(UserDTO document) {
        System.out.println("Document ID: " + document);
        if (!super.documentExists(document.getUserId())) {
            System.out.println(document);
            super.setDocument(document.getUserId(), document);
        }
    }

    public void updateUserFields(UserDTO document) {

    }
}
