package com.example.telpback.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            System.out.println("Initializing FirebaseApp");
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            System.out.println(credentials);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .setProjectId("telp-445718")
                    .build();

            FirebaseApp.initializeApp(options);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current working directory: " + currentDir);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
