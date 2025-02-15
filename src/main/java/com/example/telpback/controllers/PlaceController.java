package com.example.telpback.controllers;
import com.example.telpback.models.Picture;
import com.example.telpback.models.Place;
import com.example.telpback.services.PictureService;
import com.example.telpback.services.PlaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaceController {
    @GetMapping("/places/{id}")
    @ResponseBody
    public Place getSinglePlaceDocument(@PathVariable String id) {
        PlaceService placeService = new PlaceService();
        Place placeDocument;

        try {
            placeDocument = placeService.getSingleDocumentByName(id);
            System.out.println(placeDocument.toString());
            return placeDocument;
        } catch (Exception e) {
            System.out.println(e);
        }

        return new Place();
    }
}
