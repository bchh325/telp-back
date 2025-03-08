package com.example.telpback.generics;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class UploadService<T> {
    private static Storage storage;
    private final String bucketName;

    public UploadService(String bucketName) {
        this.bucketName = bucketName;

        storage = StorageOptions.getDefaultInstance().getService();
    }

    public void uploadToBucket(String uniqueObjectId, MultipartFile file) {
        BlobId blobId = BlobId.of(this.bucketName, uniqueObjectId);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("image/jpeg")
                .build();
        try {
            storage.create(blobInfo, file.getBytes());
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
