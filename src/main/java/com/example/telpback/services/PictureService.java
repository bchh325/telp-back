package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.generics.BaseUploadService;
import com.example.telpback.models.Picture;

public class PictureService {
    private BaseFirestoreService<Picture> firestoreService;
    private BaseUploadService<Picture> uploadService;
    public PictureService(String firestoreCollectionName, String cloudBucketName) {
        firestoreService = new BaseFirestoreService<>(firestoreCollectionName, Picture.class);
        uploadService = new BaseUploadService<>(cloudBucketName);
    }

    public PictureService(String firestoreCollectionName) {
        firestoreService = new BaseFirestoreService<>(firestoreCollectionName, Picture.class);
    }

    public Picture getSingleDocumentByName(String documentId) {
        try {
            return firestoreService.getSingleDocumentByName(documentId);
        } catch (Exception e) {
            System.out.println(e);
        }

        return new Picture();
    }

    public void upload(String uniqueObjectName, String filePath) {
        uploadService.upload(uniqueObjectName, filePath);
    }

    public void getPicture() {
        uploadService.getPicture();
    }
}
