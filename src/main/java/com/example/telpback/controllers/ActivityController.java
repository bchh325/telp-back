package com.example.telpback.controllers;

import com.example.telpback.models.Activity;
import com.example.telpback.services.ActivityService;
import jakarta.validation.Valid;
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

        System.out.println(activity);

        response.put("message", "Successfully built activity.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
