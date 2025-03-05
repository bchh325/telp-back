package com.example.telpback.controllers;
import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.models.User;
import com.example.telpback.models.ValidationResult;
import com.example.telpback.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createNewUser(@RequestBody @Valid User user) {
        try {
            ValidationResult result = userService.createUser(user);

            Map<String, Object> response = new HashMap<>();

            response.put("data", user);
            response.put("message", result.getMessage());
            response.put("error", result.isValid());

            return new ResponseEntity<>(response, result.getStatus());
        } catch (Exception e) {
            System.out.println(e);

            Map<String, Object> response = new HashMap<>();

            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public void updateFields(@RequestBody User user) {

    }
}
