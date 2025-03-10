package com.example.telpback.services;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.dto.PaginationResponseDTO;
import com.example.telpback.dto.PictureDTO;
import com.example.telpback.generics.FirestoreService;
import com.example.telpback.generics.UploadService;
import com.example.telpback.models.Picture;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {
    private final FirestoreService<Picture> firestoreService;
    private final UploadService<Picture> uploadService;

    @Autowired
    public PictureService(FirestoreService<Picture> pictureFirestoreService,
                          UploadService<Picture> pictureUploadService) {
        this.firestoreService = pictureFirestoreService;
        this.uploadService = pictureUploadService;
    }

    public Picture getSingleDocumentByName(String documentId) {
        try {
            return firestoreService.getSingleDocumentById(documentId).toObject(Picture.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        return new Picture();
    }

    public void upload(PictureDTO pictureDto, MultipartFile file) {
        Picture picture = new Picture(pictureDto);
        DocumentDTO<Picture> document = new DocumentDTO<>(picture.getUuid(), picture);
        firestoreService.setDocument(document);
        uploadService.uploadToBucket(picture, file);
    }

    public void getPicture() {
        uploadService.getPicture();
    }

    public PaginationResponseDTO refreshPaginate(String documentIdStartKey, String placeId, int querySize) {
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
        return new PaginationResponseDTO(urls, null, newestDocumentInSnapshot.getId());
    }
    public PaginationResponseDTO paginate(String documentIdStartKey, String placeId, int querySize) {
        DocumentSnapshot oldestDocumentInSnapshot = null;
        DocumentSnapshot newestDocumentInSnapshot = null;
        String resourceOriginUrl = "https://housetofusoup.com";
        List<URL> urls = new ArrayList<>();

        try {
            Query query = buildPaginationQuery(documentIdStartKey, placeId, querySize, false);
            List<QueryDocumentSnapshot> snapshots = firestoreService.executeQuery(query);

            oldestDocumentInSnapshot = snapshots.get(snapshots.size() - 1);
            newestDocumentInSnapshot = snapshots.get(0);


            for (DocumentSnapshot doc : snapshots) {
                URL constructuedUrl = new URL(resourceOriginUrl + "/" + doc.getId());
                System.out.println(constructuedUrl);
                urls.add(constructuedUrl);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("end pagination");
        return new PaginationResponseDTO(urls, oldestDocumentInSnapshot.getId(), newestDocumentInSnapshot.getId());
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
