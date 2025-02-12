package com.example.telpback.controllers;

import com.example.telpback.models.Picture;
import com.example.telpback.services.PictureService;
import org.springframework.web.bind.annotation.*;

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
    public void uploadPicture() {
        System.out.println("uploading picture");
        pictureService.upload("ObjectName", "/Users/bryan/Documents/telp-front/assets/images/background.jpg");
    }
}
