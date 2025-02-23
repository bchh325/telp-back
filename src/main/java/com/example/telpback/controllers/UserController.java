package com.example.telpback.controllers;
import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.dto.UserDTO;
import com.example.telpback.models.User;
import com.example.telpback.services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService = new UserService();

    @PostMapping("/set")
    public void setNewUser(@RequestBody UserDTO user) {
        user.setLikedPlaces(new ArrayList<>());
        user.setUploadedPictures(new ArrayList<>());
        user.setVisitedPlaces(new ArrayList<>());

        userService.setNewUser(user);
    }
}
