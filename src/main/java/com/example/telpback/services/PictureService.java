package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.generics.BaseUploadService;
import com.example.telpback.models.Picture;
import org.springframework.web.multipart.MultipartFile;

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

    public void upload(String pictureUuid, MultipartFile file, Picture pictureObject) {
       uploadService.uploadToBucket(pictureUuid, file);
       firestoreService.setDocument(pictureUuid, pictureObject);
    }

    public void getPicture() {
        uploadService.getPicture();
    }

    public void paginate(String startKey) {

    }
}
