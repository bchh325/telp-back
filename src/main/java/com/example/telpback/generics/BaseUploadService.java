package com.example.telpback.generics;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.awt.desktop.SystemSleepEvent;
import java.nio.file.Paths;

public class BaseUploadService<T> {
    private static Storage storage;
    private BlobId blobId;
    private final String bucketName;

    public BaseUploadService(String bucketName) {
        this.bucketName = bucketName;

        storage = StorageOptions.getDefaultInstance().getService();
    }

    public void upload(String uniqueObjectName, String filePath) {
        blobId = BlobId.of(this.bucketName, uniqueObjectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try {
            storage.createFrom(blobInfo, Paths.get(filePath));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
