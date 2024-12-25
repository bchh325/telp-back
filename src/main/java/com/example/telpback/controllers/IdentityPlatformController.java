package com.example.telpback.controllers;

import com.example.telpback.models.IdentityPlatform;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class IdentityPlatformController {

    @GetMapping("/greeting")
    public IdentityPlatform greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new IdentityPlatform("Testing Response");
    }

}
