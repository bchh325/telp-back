package com.example.telpback.services;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PictureServiceTests {
    private static final Logger LOGGER = LogManager.getLogger();

    @BeforeEach
    void setup(TestInfo testInfo) {
        String displayName = testInfo.getDisplayName();
        LOGGER.info("Testing: " + displayName);
    }

    /*
    Types of images and videos:
    image/jpeg, image/png, image/webp, image/heif
	video/mp4, video/quicktime
     */

    @Test
    void resizePortraitTest() throws Exception {
        URL url = new URL("https://picsum.photos/1200/2000");
        BufferedImage urlImage = ImageIO.read(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( urlImage, "jpg", baos );

        MockMultipartFile mockFile = new MockMultipartFile("name", baos.toByteArray());

        BufferedImage image = ImageIO.read(mockFile.getInputStream());
        int initialWidth = image.getWidth();
        int initialHeight = image.getHeight();

        BufferedImage resizedImage = PictureUtilities.resize(mockFile);
        int resizedWidth = resizedImage.getWidth();
        int resizedHeight = resizedImage.getHeight();

        LOGGER.info("Initial Width/Height: " + initialWidth + "/" + initialHeight);
        LOGGER.info("Resized Width: " + resizedWidth + "/" + resizedHeight);

        assertThat(Math.min(resizedWidth, resizedHeight)).isEqualTo(400);
    }

    @Test
    void resizeLandscapeTest() throws Exception {
        URL url = new URL("https://picsum.photos/2000/1200");
        BufferedImage urlImage = ImageIO.read(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( urlImage, "jpg", baos );
        baos.close();

        MockMultipartFile mockFile = new MockMultipartFile("name", baos.toByteArray());

        BufferedImage image = ImageIO.read(mockFile.getInputStream());
        int initialWidth = image.getWidth();
        int initialHeight = image.getHeight();

        BufferedImage resizedImage = PictureUtilities.resize(mockFile);
        int resizedWidth = resizedImage.getWidth();
        int resizedHeight = resizedImage.getHeight();

        LOGGER.info("Initial Width/Height: " + initialWidth + "/" + initialHeight);
        LOGGER.info("Resized Width: " + resizedWidth + "/" + resizedHeight);

        assertThat(Math.min(resizedWidth, resizedHeight)).isEqualTo(400);
    }

    @Test
    void resizeSquareTest() throws Exception {
        URL url = new URL("https://picsum.photos/2000/2000");
        BufferedImage urlImage = ImageIO.read(url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( urlImage, "jpg", baos );
        baos.close();

        MockMultipartFile mockFile = new MockMultipartFile("name", baos.toByteArray());

        BufferedImage image = ImageIO.read(mockFile.getInputStream());
        int initialWidth = image.getWidth();
        int initialHeight = image.getHeight();

        BufferedImage resizedImage = PictureUtilities.resize(mockFile);
        int resizedWidth = resizedImage.getWidth();
        int resizedHeight = resizedImage.getHeight();

        LOGGER.info("Initial Width/Height: " + initialWidth + "/" + initialHeight);
        LOGGER.info("Resized Width: " + resizedWidth + "/" + resizedHeight);

        assertThat(Math.min(resizedWidth, resizedHeight)).isEqualTo(400);
    }

}
