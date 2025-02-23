package com.example.telpback.generics;

public class BaseDocumentUpdate<T> {
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
