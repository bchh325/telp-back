package com.example.telpback.controllers;

import com.example.telpback.models.User;
import com.example.telpback.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
public class IdentityPlatformController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "deployment testing1" + name;
    }

    @GetMapping("/search")
    public String searchTest(@RequestParam(value = "name", defaultValue = "World") String name) {
        RestClient restClient = RestClient.create();
        String secret = "ENTER SECRET";

        ResponseEntity<String> result = restClient.get()
                .uri("https://api.yelp.com/v3/businesses/search?location=Los%20Angeles&term=chicken&sort_by=best_match&limit=20")
                .header("authorization", "Bearer " + secret)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);

        System.out.println(result.getBody());


        return result.getBody();
    }

}
