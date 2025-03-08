package com.example.telpback.generics;

import com.example.telpback.interfaces.Media;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UploadService<T extends Media> {

    private abstract class Uploader {
        abstract void upload(T mediaModel);
    }

    private class GeneralImageUploader extends Uploader {

        @Override
        public void upload(T mediaModel) {
//            String pictureUuid = UUID.randomUUID().toString();
//
//            BlobId blobId = BlobId.of(this.bucketName, uniqueObjectId);
//            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
//                    .setContentType("image/jpeg")
//                    .build();
//            try {
//                storage.create(blobInfo, file.getBytes());
//            } catch (Exception e) {
//                System.out.println(e);
//            }
        }
    }

    private class ProfileImageUploader extends Uploader {
        @Override
        public void upload(T mediaModel) {

        }
    }

    private class VideoUploader extends Uploader {

        @Override
        public void upload(T mediaModel) {
            String pictureUuid = UUID.randomUUID().toString();

        }

    }

    private Uploader getUploader(T mediaModel) throws Exception {
        String uploadType = mediaModel.getUploadType();
        String pictureType = mediaModel.getPictureType();

        boolean isVideoType = uploadType.equals("video");

        boolean isGeneralPicture = uploadType.equals("image") && pictureType.equals("general");
        boolean isProfilePicture = uploadType.equals("image") && pictureType.equals("profile");

        if (isVideoType) {
            return new VideoUploader();
        } else if (isProfilePicture) {
            return new GeneralImageUploader();
        } else if (isGeneralPicture) {
            return new ProfileImageUploader();
        }

        throw new Exception("Unsupported media type.");
    }

    private static Storage storage;
    private final String bucketName;

    public UploadService(String bucketName) {
        this.bucketName = bucketName;
        storage = StorageOptions.getDefaultInstance().getService();
    }

    public void uploadToBucket(T mediaModel, MultipartFile file) {
        try {
            Uploader uploader = this.getUploader(mediaModel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void getPicture() {
        BlobId blobId = BlobId.of(this.bucketName, "testimage.jpg");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        System.out.println(url);

    }
}
