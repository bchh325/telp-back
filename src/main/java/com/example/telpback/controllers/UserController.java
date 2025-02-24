package com.example.telpback.controllers;
import com.example.telpback.models.User;
import com.example.telpback.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService = new UserService();

    @PostMapping("/create")
    public void setNewUser(@RequestBody User user) {
        user.setLikedPlaces(new ArrayList<>());
        user.setUploadedPictures(new ArrayList<>());
        user.setVisitedPlaces(new ArrayList<>());

        userService.setNewUser(user);
    }

    @PostMapping("/update")
    public void updateFields(@RequestBody User user) {

    }
}
