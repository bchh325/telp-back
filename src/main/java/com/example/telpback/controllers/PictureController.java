package com.example.telpback.controllers;

import com.example.telpback.dto.DocumentDTO;
import com.example.telpback.dto.PaginationResponseDTO;
import com.example.telpback.models.Picture;
import com.example.telpback.services.PictureService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.UUID;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    PictureService pictureService = new PictureService("pictures", "telp-photos");

    @GetMapping("/{id}")
    @ResponseBody
    public Picture getSinglePictureDocument(@PathVariable String id) {
        PictureService pictureService = new PictureService("pictures", "telp-photos");

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
        Picture picture = new Picture(placeId);

        System.out.println("uploading picture");
        pictureService.upload(pictureUuid, picture, file);
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
        Picture picture = new Picture(placeId);


        if (multipartFile != null) {
            System.out.println("uploading picture");
            pictureService.upload(pictureUuid, picture, multipartFile);
        }
    }

    @GetMapping("/get")
    public void getPicture() {
        System.out.println("getting picture url");
        pictureService.getPicture();
    }

    @GetMapping("/paginate")
    public PaginationResponseDTO getPaginatedContentUrls(
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
