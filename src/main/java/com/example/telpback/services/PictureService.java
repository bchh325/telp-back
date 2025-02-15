package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.generics.BaseUploadService;
import com.example.telpback.models.Picture;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
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

    public DocumentSnapshot paginate(DocumentSnapshot startKey, String placeId) {
        int querySize = 2;
        DocumentSnapshot lastDocumentInSnapshot = null;

        CollectionReference collectionReference = firestoreService.getRef();
        Query query = collectionReference
                .whereEqualTo("placeId", "place_id_3")
                .orderBy("timestamp")
                .startAfter(startKey)
                .limit(querySize);

        try {
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            QuerySnapshot snapshot = querySnapshot.get();

            lastDocumentInSnapshot = snapshot.getDocuments().get(snapshot.size() - 1);

            for (DocumentSnapshot doc : snapshot) {
                System.out.println(doc.getId());
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return lastDocumentInSnapshot;
    }
}
