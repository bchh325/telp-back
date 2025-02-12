package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Picture;

public class PictureService extends BaseFirestoreService<Picture> {
    public PictureService(String collectionName) {
        super(collectionName, Picture.class);
    }

    public static void uploadObject() {

    }
}
