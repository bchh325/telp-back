package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Picture;

public class PictureService extends BaseFirestoreService<Picture> {
    public PictureService() {
        super("pictures", Picture.class);
    }
}
