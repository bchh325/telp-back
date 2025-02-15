package com.example.telpback.controllers;

import com.example.telpback.models.Picture;
import com.example.telpback.models.Place;
import com.example.telpback.services.PictureService;
import org.springframework.web.bind.annotation.*;

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
    public void uploadPicture(@RequestBody Picture picture) {
        UUID uuid = UUID.randomUUID();

        System.out.println("uploading picture");
        pictureService.upload(uuid.toString(), "/Users/bryan/Documents/telp-front/assets/images/background.jpg");
    }

    @GetMapping("/get")
    public void getPicture() {
        System.out.println("getting picture url");
        pictureService.getPicture();
    }

    @GetMapping("/paginate")
    public Place[] getPaginatedPicturesStartingFrom(String startKey, int amount) {
        return new Place[]{};
    }
}
