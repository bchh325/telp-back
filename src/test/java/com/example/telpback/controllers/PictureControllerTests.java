package com.example.telpback.controllers;

import com.example.telpback.models.Picture;
import com.example.telpback.services.PictureService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(PictureController.class)
public class PictureControllerTests {

    @MockitoBean
    private PictureService pictureService;


    @Test
    void contextLoads() throws Exception {

    }

}
