package com.example.telpback.controllers;
import com.example.telpback.validators.UserCreationConstraints;
import com.example.telpback.validators.UserUpdateConstraints;
import com.example.telpback.models.User;
import com.example.telpback.models.ValidationResult;
import com.example.telpback.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    public UserController(UserService service) {
        this.userService = service;
    }

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createNewUser(
            @Validated(UserCreationConstraints.class)
            @RequestBody User user) {
        try {
            ValidationResult result = userService.createUser(user);

            Map<String, Object> response = new HashMap<>();

            response.put("data", user);
            response.put("message", result.getMessage());
            response.put("error", result.getErrorStatus());

            return new ResponseEntity<>(response, result.getHttpStatus());
        } catch (Exception e) {
            System.out.println(e);

            Map<String, Object> response = new HashMap<>();

            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> updateFields(
            @Validated(UserUpdateConstraints.class)
            @RequestBody User user) {

        try {
            ValidationResult result = userService.updateUser(user);

            Map<String, Object> response = new HashMap<>();

            response.put("data", user);
            response.put("message", result.getMessage());
            response.put("error", result.getErrorStatus());

            return new ResponseEntity<>(response, result.getHttpStatus());
        } catch (Exception e) {
            System.out.println(e);

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Error updating document.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
