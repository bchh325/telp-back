package com.example.telpback.controllers;
import com.example.telpback.generics.BaseDocumentUpdate;
import com.example.telpback.models.Place;
import com.example.telpback.services.PlaceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
public class PlaceController {
    PlaceService placeService = new PlaceService("places");

    @GetMapping("/{id}")
    @ResponseBody
    public Place getSinglePlaceDocument(@PathVariable String id) {
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

    @PostMapping("/update")
    public void updatePlace(@RequestBody BaseDocumentUpdate<Place> body) {
        try {
            placeService.updateDocument(body.getObject(), body.getDocumentId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
