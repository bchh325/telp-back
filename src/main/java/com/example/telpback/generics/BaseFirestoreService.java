package com.example.telpback.generics;

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

    public T getSingleDocumentByName(String documentId) throws Exception {
        DocumentReference docRef = this.ref.document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot singleDocument = future.get();

        if (singleDocument.exists()) {
            System.out.println(singleDocument.getData());
            return singleDocument.toObject(this.type);
        } else {
            throw new DocumentNotFoundException("There was an error retrieving the selected document.");
        }
    }
}
