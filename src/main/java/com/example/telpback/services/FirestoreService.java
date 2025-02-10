package com.example.telpback.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;

import java.util.List;

public class FirestoreService {
    private static Firestore db;

    public FirestoreService() {
        if (db == null) {
            synchronized (FirestoreService.class) {
                if (db == null) {
                    db = FirestoreClient.getFirestore();
                }
            }
        }
    }

    public void getDocuments() {
        try {
            ApiFuture<QuerySnapshot> query = db.collection("users").get();
            QuerySnapshot snapshot = query.get();
            List<QueryDocumentSnapshot> documents = snapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                System.out.println("Document ID: " + document.getId());
                System.out.println("Data: " + document.getData());
                System.out.println("--------------------");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
