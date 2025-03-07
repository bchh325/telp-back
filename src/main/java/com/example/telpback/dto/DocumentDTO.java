package com.example.telpback.dto;

import com.google.cloud.firestore.annotation.Exclude;

public class DocumentDTO<T> {
    public DocumentDTO(String documentId, T object) {
        this.documentId = documentId;
        this.object = object;
    }

    private final String documentId;
    private final T object;

    @Exclude
    public String getDocumentId() {
        return documentId;
    }


    public T getObject() {
        return object;
    }


    @Override
    public String toString() {
        return "DocumentDTO{" +
                "documentId='" + documentId + '\'' +
                ", object=" + object +
                '}';
    }
}
