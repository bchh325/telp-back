package com.example.telpback.controllers;

import com.example.telpback.models.Activity;
import com.example.telpback.services.ActivityService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    public ActivityController(ActivityService service) { this.activityService = service; }

    private final ActivityService activityService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(
            @Valid
            @RequestBody Activity activity
            ) {

        Map<String, Object> response = new HashMap<>();

        boolean likedPlacesExists = activity.getLikedPlaces() != null;
        boolean visitedPlacesExists = activity.getVisitedPlaces() != null;

        response.put("message", "Successfully deserialized activity");

        try {
            if (likedPlacesExists) {
                activityService.addToLiked(activity);
            } else if (visitedPlacesExists) {
                activityService.addToVisits(activity);
            }
        } catch (Exception e) {

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error adding to list");

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
