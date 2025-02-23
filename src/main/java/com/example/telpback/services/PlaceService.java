package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Place;

public class PlaceService extends BaseFirestoreService<Place> {
    public PlaceService() {
        super("places", Place.class);
    }

    public void incrementLikeAmount(String placeId) {

    }

    public void decrementLikeAmount(String placeId) {

    }
}
