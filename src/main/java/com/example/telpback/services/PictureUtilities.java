package com.example.telpback.services;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PictureUtilities {
    public static BufferedImage resize(MultipartFile file, int size) throws Exception {
        BufferedImage image = ImageIO.read(file.getInputStream());

        int width = image.getWidth();
        int height = image.getHeight();

        if (width < height) {
            return Scalr.resize(image, Scalr.Mode.FIT_TO_WIDTH, size);
        } else if (height < width) {
            return Scalr.resize(image, Scalr.Mode.FIT_TO_HEIGHT, size);
        } else {
            return Scalr.resize(image, Scalr.Method.AUTOMATIC, size);
        }
    }
}
