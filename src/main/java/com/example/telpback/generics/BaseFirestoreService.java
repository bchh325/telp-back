package com.example.telpback.generics;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.exceptions.DocumentNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.List;

public class BaseFirestoreService<T> {
    private static Firestore db;
    private CollectionReference ref;
    private final Class<T> type;

    public BaseFirestoreService(String collectionName, Class<T> type) {
        this.type = type;
        if (db == null) {
            synchronized (BaseFirestoreService.class) {
                if (db == null) {
                    db = FirestoreClient.getFirestore();
                }
            }
        }

        if (db != null) {
            this.ref = db.collection(collectionName);
        }
    }

    public CollectionReference getRef() {
        return ref;
    }

    public void setRef(CollectionReference ref) {
        this.ref = ref;
    }

    public boolean documentExists(String documentId) {
        DocumentReference docRef = this.ref.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot snapshot = future.get();
            return snapshot.exists();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }

    public DocumentSnapshot getSingleDocumentById(String documentId) throws Exception {
        DocumentReference docRef = this.ref.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot singleDocument = future.get();

        if (singleDocument.exists()) {
            return singleDocument;
        } else {
            throw new DocumentNotFoundException("There was an error retrieving the selected document.");
        }
    }

    public void setDocument(String documentId, Object document) {
        try {
            ref.document(documentId).set(document);
        } catch (Exception e) {
            System.out.println("Error setting document");
            System.out.println(e);
        }
    }

    public void addDocument(T documentObject) {
        try {
            ref.add(documentObject);
        } catch (Exception e) {
            System.out.println("Error adding document");
            System.out.println(e);
        }
    }

    public void updateDocument(String documentId, Object documentObject) {

    }
}
