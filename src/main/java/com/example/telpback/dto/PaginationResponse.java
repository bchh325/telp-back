package com.example.telpback.dto;

import java.net.URL;
import java.util.List;

public class PaginationResponse {
    private List<URL> urls;
    private String documentIdStartKey;
    private String documentIdRefreshKey;

    public PaginationResponse() {}

    public PaginationResponse(List<URL> urls, String documentIdStartKey, String documentIdRefreshKey) {
        this.urls = urls;
        this.documentIdStartKey = documentIdStartKey;
        this.documentIdRefreshKey = documentIdRefreshKey;
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

    public String getDocumentIdRefreshKey() {
        return documentIdRefreshKey;
    }

    public void setDocumentIdRefreshKey(String documentIdRefreshKey) {
        this.documentIdRefreshKey = documentIdRefreshKey;
    }
}
