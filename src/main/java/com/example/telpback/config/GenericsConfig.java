package com.example.telpback.config;

import com.example.telpback.dto.ActivityDTO;
import com.example.telpback.generics.FirestoreService;
import com.example.telpback.generics.UploadService;
import com.example.telpback.models.Activity;
import com.example.telpback.models.Follower;
import com.example.telpback.models.Picture;
import com.example.telpback.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GenericsConfig {

    @Bean
    public FirestoreService<Picture> pictureFirestoreService() { return new FirestoreService<>("pictures", Picture.class); }
    @Bean
    public UploadService<Picture> pictureUploadService() { return new UploadService<>("telp-photos"); }
    @Bean
    public FirestoreService<User> userFirestoreService() { return new FirestoreService<>("users", User.class); }
    @Bean
    public FirestoreService<Follower> followersFirestoreService() { return new FirestoreService<>("followers", Follower.class); }
    @Bean
    public FirestoreService<ActivityDTO> userlikesFirestoreService() { return new FirestoreService<>("userlikes", ActivityDTO.class); }
    @Bean
    public FirestoreService<ActivityDTO> uservisitsFirestoreService() { return new FirestoreService<>("uservisits", ActivityDTO.class); }

}
