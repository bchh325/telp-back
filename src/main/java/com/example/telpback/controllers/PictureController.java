package com.example.telpback.controllers;

import com.example.telpback.dto.PaginationResponse;
import com.example.telpback.models.Picture;
import com.example.telpback.models.Place;
import com.example.telpback.services.PictureService;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    PictureService pictureService = new PictureService("pictures", "telp-photos");

    @GetMapping("/{id}")
    @ResponseBody
    public Picture getSinglePictureDocument(@PathVariable String id) {
        PictureService pictureService = new PictureService();
        Picture pictureDocument;

        try {
            pictureDocument = pictureService.getSingleDocumentByName(id);
            System.out.println(pictureDocument.toString());
            return pictureDocument;
        } catch (Exception e) {
            System.out.println(e);
        }

        return new Picture();
    }

    @PostMapping("/upload")
    public void uploadPicture(
            @RequestParam("file") MultipartFile file,
            @RequestParam("placeId") String placeId
    ) {
        String pictureUuid = UUID.randomUUID().toString();

        Picture pictureObject = new Picture(placeId);

        System.out.println("uploading picture");
        pictureService.upload(pictureUuid, file, pictureObject);
    }

    @PostMapping("/upload/dev")
    public void uploadPictureDev(
            @RequestParam("link") String link,
            @RequestParam("placeId") String placeId
    ) {
        MultipartFile multipartFile = null;
        try {
            URL imageUrl = new URL(link);
            BufferedImage image = ImageIO.read(imageUrl);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "jpg", baos );
            multipartFile = new MockMultipartFile("name", baos.toByteArray());
            baos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String pictureUuid = UUID.randomUUID().toString();

        Picture pictureObject = new Picture(placeId);

        System.out.println("uploading picture");
        if (multipartFile != null) {
            pictureService.upload(pictureUuid, multipartFile, pictureObject);
        }
    }

    @GetMapping("/get")
    public void getPicture() {
        System.out.println("getting picture url");
        pictureService.getPicture();
    }

    @GetMapping("/paginate")
    public PaginationResponse getPaginatedContentUrls(
            @RequestParam(required = false) String documentIdKeyCursor,
            @RequestParam String placeId,
            @RequestParam(defaultValue = "5") int querySize,
            @RequestParam(defaultValue = "false") boolean isRefresh
    ) {
        if (documentIdKeyCursor == null) {
            documentIdKeyCursor = "";
        }

        if (isRefresh) {
            return pictureService.refreshPaginate(documentIdKeyCursor, placeId, querySize);
        } else {
            return pictureService.paginate(documentIdKeyCursor, placeId, querySize);
        }
    }
}
