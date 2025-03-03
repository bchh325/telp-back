package com.example.telpback;

import com.example.telpback.config.FirebaseConfig;
import com.example.telpback.controllers.PictureController;
import com.example.telpback.controllers.PlaceController;
import com.example.telpback.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(FirebaseConfig.class)
public class TelpBackApplicationTests {
    @Test
    void contextLoads() {

    }
}
