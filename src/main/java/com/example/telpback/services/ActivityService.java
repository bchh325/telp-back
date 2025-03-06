package com.example.telpback.services;

import com.example.telpback.generics.FirestoreService;
import com.example.telpback.models.Activity;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final FirestoreService<Activity> userlikesService;

    public ActivityService(FirestoreService<Activity> userlikesFirestoreService) {
        this.userlikesService = userlikesFirestoreService;
    }

}
