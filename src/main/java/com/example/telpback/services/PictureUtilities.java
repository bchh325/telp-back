package com.example.telpback.services;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PictureUtilities {
    public static BufferedImage resize(MultipartFile file) throws Exception {
        BufferedImage image = ImageIO.read(file.getInputStream());

        int width = image.getWidth();
        int height = image.getHeight();

        if (width < height) {
            return Scalr.resize(image, Scalr.Mode.FIT_TO_WIDTH, 400);
        } else if (height < width) {
            return Scalr.resize(image, Scalr.Mode.FIT_TO_HEIGHT, 400);
        } else {
            return Scalr.resize(image, Scalr.Method.AUTOMATIC, 400);
        }
    }
}
