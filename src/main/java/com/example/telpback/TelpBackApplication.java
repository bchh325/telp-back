package com.example.telpback;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class TelpBackApplication {
    public static void main(String[] args) {
        try {
            FileInputStream serviceAccount = new FileInputStream("telpServiceAccount.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Current working directory: " + currentDir);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        SpringApplication.run(TelpBackApplication.class, args);
    }

}
