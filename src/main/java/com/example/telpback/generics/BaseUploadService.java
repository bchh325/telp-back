package com.example.telpback.generics;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.awt.desktop.SystemSleepEvent;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

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
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("image/jpeg")
                .build();
        try {
            storage.createFrom(blobInfo, Paths.get(filePath));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getPicture() {
        BlobId blobId = BlobId.of(this.bucketName, "testimage.jpg");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        System.out.println(url);

    }
}
