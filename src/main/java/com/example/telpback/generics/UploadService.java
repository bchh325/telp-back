package com.example.telpback.generics;

import com.example.telpback.interfaces.Media;
import com.example.telpback.services.PictureUtilities;
import com.google.cloud.ByteArray;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class UploadService<T extends Media> {
    private static Storage storage;

    public UploadService() {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    private abstract class Uploader {
        abstract void upload(T mediaModel, MultipartFile file) throws Exception;
    }

    private class GeneralImageUploader extends Uploader {

        @Override
        public void upload(T mediaModel, MultipartFile file) throws Exception {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(PictureUtilities.resize(file), "jpg", baos);

            byte[] originalImageBytes = file.getBytes();
            byte[] thumbImageBytes = baos.toByteArray();

            BlobId originalBlobId = BlobId.of("telp-photos", mediaModel.getUuid());
            BlobId thumbBlobId = BlobId.of("telp-photos", mediaModel.getUuid().concat("-thumb"));

            BlobInfo originalBlobInfo = BlobInfo
                    .newBuilder(originalBlobId)
                    .setContentType("image/jpeg")
                    .build();

            BlobInfo thumbBlobInfo = BlobInfo
                    .newBuilder(thumbBlobId)
                    .setContentType("image/jpeg")
                    .build();

            storage.create(originalBlobInfo, originalImageBytes);
            storage.create(thumbBlobInfo, thumbImageBytes);
        }
    }

    private class ProfileImageUploader extends Uploader {
        @Override
        public void upload(T mediaModel, MultipartFile file) throws Exception {

        }
    }

    private class VideoUploader extends Uploader {

        @Override
        public void upload(T mediaModel, MultipartFile file) throws Exception {
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
            System.out.println("Returning video uploader");
            return new VideoUploader();
        } else if (isProfilePicture) {
            System.out.println("Returning general image uploader");
            return new GeneralImageUploader();
        } else if (isGeneralPicture) {
            System.out.println("Returning profile image uploader");
            return new ProfileImageUploader();
        }

        throw new Exception("Unsupported media type.");
    }

    public void uploadToBucket(T mediaModel, MultipartFile file) {
        try {
            Uploader uploader = this.getUploader(mediaModel);
            System.out.println(mediaModel.getUuid());
            uploader.upload(mediaModel, file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void getPicture() {
        BlobId blobId = BlobId.of("telp-photos", "testimage.jpg");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        URL url = storage.signUrl(blobInfo, 15, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
        System.out.println(url);

    }
}
