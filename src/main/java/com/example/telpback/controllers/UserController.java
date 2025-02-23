package com.example.telpback.controllers;
import com.example.telpback.models.User;
import com.example.telpback.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/users/{id}")
    @ResponseBody
    public User getSingleUserDocument(@PathVariable String id) {
        UserService userService = new UserService();
        User userDocument;

        try {
            userDocument = userService.getSingleDocumentById(id).toObject(User.class);
            System.out.println(userDocument.toString());
            return userDocument;
        } catch (Exception e) {
            System.out.println(e);
        }

        return new User();
    }
}
