package com.example.telpback.controllers;

import com.example.telpback.dto.PaginationResponse;
import com.example.telpback.models.Picture;
import com.example.telpback.models.Place;
import com.example.telpback.services.PictureService;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/get")
    public void getPicture() {
        System.out.println("getting picture url");
        pictureService.getPicture();
    }

    @GetMapping("/paginate")
    public PaginationResponse getPaginatedPicturesStartingFrom(
            @RequestParam(required = false) String documentIdStartKey,
            @RequestParam String placeId,
            @RequestParam(defaultValue = "5") int querySize
    ) {
        if (documentIdStartKey == null) {
            documentIdStartKey = "";
        }

        return pictureService.paginate(documentIdStartKey, placeId, querySize);
    }
}
