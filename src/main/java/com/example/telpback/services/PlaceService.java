package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Place;
import org.springframework.stereotype.Service;

@Service
public class PlaceService extends BaseFirestoreService<Place> {
    public PlaceService() {
        super("places", Place.class);
    }
}
