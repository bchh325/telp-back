package com.example.telpback.controllers;

import com.example.telpback.models.Follower;
import com.example.telpback.models.ValidationResult;
import com.example.telpback.services.FollowerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/followers")
public class FollowerController {
    public FollowerController (FollowerService service) { this.followerService = service; }

    private final FollowerService followerService;

    @PostMapping("/follow")
    public ResponseEntity<Map<String, Object>> followUser(
            @Valid
            @RequestBody(required = true) Follower followObject) {

        try {
            ValidationResult result = followerService.followUser(followObject);

            Map<String, Object> response = new HashMap<>();

            response.put("data", followObject);
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

    @PostMapping("/unfollow")
    public ResponseEntity<Map<String, Object>> unfollowUser(
            @Valid
            @RequestBody(required = true) Follower followObject) {

        try {
            ValidationResult result = followerService.unfollowUser(followObject);

            Map<String, Object> response = new HashMap<>();

            response.put("data", followObject);
            response.put("message", result.getMessage());
            response.put("error", result.getErrorStatus());

            return new ResponseEntity<>(response, result.getHttpStatus());
        } catch (Exception e) {
            System.out.println(e);

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Error deleting document.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
