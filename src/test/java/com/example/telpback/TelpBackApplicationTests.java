package com.example.telpback;

import com.example.telpback.config.FirebaseConfig;
import com.example.telpback.config.GenericsConfig;
import com.example.telpback.controllers.PictureController;
import com.example.telpback.controllers.PlaceController;
import com.example.telpback.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TelpBackApplicationTests {


    @Autowired
    private PictureController pictureController;

    @Autowired
    private UserController userController;

    @Autowired
    private PlaceController placeController;

    @Test
    void contextLoads() throws Exception {

        assertThat(pictureController).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(placeController).isNotNull();

    }
}
