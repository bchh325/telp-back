package com.example.telpback.dto;

public class DocumentDTO<T> {
    public DocumentDTO(String documentId, T object) {
        this.documentId = documentId;
        this.object = object;
    }

    private String documentId;
    private T object;

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
}
