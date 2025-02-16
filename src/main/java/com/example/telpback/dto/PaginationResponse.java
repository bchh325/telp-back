package com.example.telpback.dto;

import java.net.URL;
import java.util.List;

public class PaginationResponse {
    private List<URL> urls;
    private String documentIdStartKey;

    public PaginationResponse() {}

    public PaginationResponse(List<URL> urls, String documentIdStartKey) {
        this.urls = urls;
        this.documentIdStartKey = documentIdStartKey;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }

    public String getDocumentIdStartKey() {
        return documentIdStartKey;
    }

    public void setDocumentIdStartKey(String documentIdStartKey) {
        this.documentIdStartKey = documentIdStartKey;
    }
}
