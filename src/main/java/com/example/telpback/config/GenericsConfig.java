package com.example.telpback.config;

import com.example.telpback.generics.FirestoreService;
import com.example.telpback.generics.UploadService;
import com.example.telpback.models.Activity;
import com.example.telpback.models.Follower;
import com.example.telpback.models.Picture;
import com.example.telpback.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public FirestoreService<Activity> userlikesFirestoreService() { return new FirestoreService<>("userlikes", Activity.class); }

}
