package com.example.telpback.generics;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.exceptions.DocumentNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BaseFirestoreService<T> {
    private static Firestore db;
    private CollectionReference ref;

    public BaseFirestoreService(String collectionName, Class<T> type) {
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

    public boolean documentExists(String documentId) throws Exception {
        DocumentReference docRef = this.ref.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot snapshot = future.get();
            return snapshot.exists();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
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

    public boolean setDocument(DocumentDTO<T> document) {
        try {
            String documentId = document.getDocumentId();
            T fields = document.getObject();
            System.out.println("Setting DocumentId " + documentId);
            System.out.println("Body " + document);
            System.out.println();
            ref.document(documentId).set(fields);
            return true;
        } catch (Exception e) {
            System.out.println("Error setting document");
            System.out.println(e);
            return false;
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

    public QuerySnapshot executeQuery(Query query) throws Exception{
        try {
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            return querySnapshot.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
