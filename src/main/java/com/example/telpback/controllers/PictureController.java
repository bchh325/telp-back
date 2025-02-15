package com.example.telpback.controllers;

import com.example.telpback.models.Picture;
import com.example.telpback.services.PictureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PictureController {
    @GetMapping("/pictures/{id}")
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
}
