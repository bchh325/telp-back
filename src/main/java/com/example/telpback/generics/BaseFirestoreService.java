package com.example.telpback.generics;

import com.example.telpback.exceptions.DocumentNotFoundException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //Creates document with auto-generated documentId. Use setDocument() if you want to specify a custom id.
    public void addDocument(T pojo) throws Exception {
        try {
            this.ref.document().set(pojo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setDocument(T pojo, String documentId) throws Exception {
        try {
            this.ref.document(documentId).set(pojo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateDocument(T pojoUpdateObject, String documentId) {
        Field[] fields = pojoUpdateObject.getClass().getDeclaredFields();
        Map<String, Object> updateMap = new HashMap<>();

        try {
            for (Field field: fields) {
                field.setAccessible(true);

                if (field.get(pojoUpdateObject) != null) {
                    updateMap.put(field.getName(), field.get(pojoUpdateObject));
                }
            }

            this.ref.document(documentId).update(updateMap);

        } catch (Exception e) {
            System.out.println("Exception has occurred: " + e);
        }
    }
}
