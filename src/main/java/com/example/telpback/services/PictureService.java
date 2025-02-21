package com.example.telpback.services;

import com.example.telpback.dto.PaginationResponse;
import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.generics.BaseUploadService;
import com.example.telpback.models.Picture;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
            return firestoreService.getSingleDocumentById(documentId).toObject(Picture.class);
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

    public PaginationResponse refreshPaginate(String documentIdStartKey, String placeId, int querySize) {
        DocumentSnapshot newestDocumentInSnapshot = null;
        String resourceOriginUrl = "https://housetofusoup.com";
        List<URL> urls = new ArrayList<>();

        try {
            Query query = buildPaginationQuery(documentIdStartKey, placeId, querySize, true);

            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            QuerySnapshot snapshot = querySnapshot.get();

            newestDocumentInSnapshot = snapshot.getDocuments().get(snapshot.size() - 1);

            for (DocumentSnapshot doc : snapshot) {
                URL constructuedUrl = new URL(resourceOriginUrl + "/" + doc.getId());
                System.out.println(constructuedUrl);
                urls.add(constructuedUrl);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("end pagination");
        return new PaginationResponse(urls, null, newestDocumentInSnapshot.getId());
    }
    public PaginationResponse paginate(String documentIdStartKey, String placeId, int querySize) {
        DocumentSnapshot oldestDocumentInSnapshot = null;
        DocumentSnapshot newestDocumentInSnapshot = null;
        String resourceOriginUrl = "https://housetofusoup.com";
        List<URL> urls = new ArrayList<>();

        try {
            Query query = buildPaginationQuery(documentIdStartKey, placeId, querySize, false);

            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            QuerySnapshot snapshot = querySnapshot.get();

            oldestDocumentInSnapshot = snapshot.getDocuments().get(snapshot.size() - 1);
            newestDocumentInSnapshot = snapshot.getDocuments().get(0);


            for (DocumentSnapshot doc : snapshot) {
                URL constructuedUrl = new URL(resourceOriginUrl + "/" + doc.getId());
                System.out.println(constructuedUrl);
                urls.add(constructuedUrl);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("end pagination");
        return new PaginationResponse(urls, oldestDocumentInSnapshot.getId(), newestDocumentInSnapshot.getId());
    }

    private Query buildPaginationQuery(String documentIdKeyCursor, String placeId, int querySize, boolean isRefresh) {
        CollectionReference collectionReference = this.firestoreService.getRef();
        DocumentSnapshot emptySnapshot = null;

        Query query = collectionReference
                .whereEqualTo("placeId", placeId);

        if (isRefresh) {
            query = query.orderBy("timestamp", Query.Direction.ASCENDING);
        } else {
            query = query.orderBy("timestamp", Query.Direction.DESCENDING);
        }

        if (documentIdKeyCursor.isBlank()) {
            return query.limit(querySize);
        }

        try {
            DocumentSnapshot startKey = this.firestoreService.getSingleDocumentById(documentIdKeyCursor);
            query = query.startAfter(startKey);
        } catch (Exception e) {
            System.out.println(e);
            query = query.startAfter(emptySnapshot);
        }

        return query.limit(querySize);
    }
}
