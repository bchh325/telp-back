package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.User;

public class UserService extends BaseFirestoreService<User> {
    public UserService() {
        super("users", User.class);
    }
}
