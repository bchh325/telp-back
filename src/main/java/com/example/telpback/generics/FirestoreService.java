package com.example.telpback.generics;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.exceptions.DocumentNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreService<T> {
    private static Firestore db;
    private CollectionReference ref;

    public FirestoreService(String collectionName, Class<T> type) {
        if (db == null) {
            synchronized (FirestoreService.class) {
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
            ref.document(documentId).set(fields);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public boolean addDocument(T documentObject) {
        try {
            ref.add(documentObject);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding document");
            System.out.println(e);
            return false;
        }
    }

    public boolean updateDocument(DocumentDTO<T> document) throws Exception {
        try {
            String documentId = document.getDocumentId();
            DocumentReference docRef = this.ref.document(documentId);

            Map<String, Object> updateMap = new HashMap<>();

            T updateObject = document.getObject();
            for (Field field: updateObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object fieldValue = field.get(updateObject);

                boolean fieldExists = fieldValue != null;
                if (fieldExists && (fieldValue != documentId)) {
                    updateMap.put(fieldName, fieldValue);
                }
            }

            if (!updateMap.isEmpty()) {
                docRef.update(updateMap).get();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public boolean deleteDocument(DocumentReference document) throws Exception {
        try {
            ApiFuture<WriteResult> writeResult = document.delete();
            WriteResult result = writeResult.get();
            System.out.println(result);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public List<QueryDocumentSnapshot> executeQuery(Query query) throws Exception {
        try {
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> snapshots = querySnapshot.get().getDocuments();
            //System.out.println(snapshot);
            return  snapshots;
        } catch (Exception e) {
            System.out.println("Query Execution Error");
            throw e;
        }
    }
}
