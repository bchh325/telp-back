package com.example.telpback.controllers;

import com.example.telpback.models.Activity;
import com.example.telpback.models.ValidationResult;
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
        ValidationResult result;

        boolean likedPlacesExists = activity.getLikedPlaces() != null;
        boolean visitedPlacesExists = activity.getVisitedPlaces() != null;

        response.put("message", "Successfully deserialized activity");
        System.out.println(activity);

        try {
            if (likedPlacesExists) {
                System.out.println("adding to liked");
               result = activityService.addToLikes(activity);
            } else if (visitedPlacesExists)  {
                System.out.println("adding to visits");
               result = activityService.addToVisits(activity);
            } else {
                result = new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error processing request.");
            }

            response.put("data", activity);
            response.put("message", result.getMessage());
            response.put("error", result.getErrorStatus());

            return new ResponseEntity<>(response, result.getHttpStatus());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error adding to list");
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<Map<String, Object>> remove(
            @Valid
            @RequestBody Activity activity
    ) {

        Map<String, Object> response = new HashMap<>();
        ValidationResult result;

        boolean likedPlacesExists = activity.getLikedPlaces() != null;
        boolean visitedPlacesExists = activity.getVisitedPlaces() != null;

        response.put("message", "Successfully deserialized activity");
        System.out.println(activity);

        try {
            if (likedPlacesExists) {
                System.out.println("removing to liked");
                result = activityService.removeFromLikes(activity);
            } else if (visitedPlacesExists)  {
                System.out.println("removing to visits");
                result = activityService.removeFromVisits(activity);
            } else {
                result = new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error processing request.");
            }

            response.put("data", activity);
            response.put("message", result.getMessage());
            response.put("error", result.getErrorStatus());

            return new ResponseEntity<>(response, result.getHttpStatus());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error removing to list");
            errorResponse.put("message", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
