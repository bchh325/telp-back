package com.example.telpback.dto;

import com.google.cloud.firestore.annotation.Exclude;

public class DocumentDTO<T> {
    public DocumentDTO(String documentId, T object) {
        this.documentId = documentId;
        this.object = object;
    }

    private String documentId;
    private T object;

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "documentId='" + documentId + '\'' +
                ", object=" + object +
                '}';
    }
}
