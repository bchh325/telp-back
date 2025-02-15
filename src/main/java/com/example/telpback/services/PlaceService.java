package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Place;

public class PlaceService extends BaseFirestoreService<Place> {
    public PlaceService(String collectionName) {
        super(collectionName, Place.class);
    }

    public void incrementLikeAmount(String placeId) {
        try {
            int currentLikes = super.getSingleDocumentByName(placeId).getLikeAmount();
            Place placeUpdate = new Place();

            placeUpdate.setLikeAmount(currentLikes + 1);

            super.updateDocument(placeUpdate, placeId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void decrementLikeAmount(String placeId) {
        try {
            int currentLikes = super.getSingleDocumentByName(placeId).getLikeAmount();
            Place placeUpdate = new Place();

            placeUpdate.setLikeAmount(currentLikes - 1);

            super.updateDocument(placeUpdate, placeId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
