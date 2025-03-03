package com.example.telpback.config;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.generics.BaseUploadService;
import com.example.telpback.models.Picture;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericsConfig {

    @Bean
    public BaseFirestoreService<Picture> pictureBaseFirestoreService() {
        return new BaseFirestoreService<>("pictures", Picture.class);
    }

    @Bean
    public BaseUploadService<Picture> pictureBaseUploadService() {
        return new BaseUploadService<>("telp-photos");
    }

}
