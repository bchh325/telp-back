package com.example.telpback.services;

import com.example.telpback.generics.FirestoreService;
import com.example.telpback.models.Activity;
import com.example.telpback.models.ValidationResult;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.SetOptions;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import javax.accessibility.AccessibleIcon;
import java.util.HashMap;
import java.util.Map;

@Service
public class ActivityService {

    private final FirestoreService<Map> userlikesFirestoreService;
    private final FirestoreService<Map> uservisitsFirestoreService;

    public ActivityService(FirestoreService<Map> userlikesFirestoreService,
                           FirestoreService<Map> uservisitsFirestoreService) {
        this.userlikesFirestoreService = userlikesFirestoreService;
        this.uservisitsFirestoreService = uservisitsFirestoreService;
    }

    public ValidationResult addToLiked(Activity activity) throws Exception {
        String userId = activity.getUserId();

        Query userDocumentExistsQuery = userlikesFirestoreService
                .getRef()
                .whereEqualTo("userId", userId);

        boolean userDocumentExists = !userlikesFirestoreService.executeQuery(userDocumentExistsQuery).isEmpty();

        if (userDocumentExists){
            System.out.println("Found Document");
            DocumentSnapshot userDocument = userlikesFirestoreService.executeQuery(userDocumentExistsQuery).get(0);
            String documentId = userDocument.getId();

            System.out.println(documentId);
            DocumentReference docRef = userlikesFirestoreService.getRef().document(documentId);


            Map<String, Object> updateMap = new HashMap<>();
            Map<String, Object> testNested = new HashMap<>();

            testNested.put("testNest", "nesting");
            updateMap.put("likedPlaces." + "testingPlaceId", testNested);

            docRef.set(updateMap, SetOptions.mergeFields("likedPlaces"));

        } else {
            Map <String, Object> addMap = new HashMap<>();

            System.out.println("attempting to add activity");
            System.out.println(activity);

            addMap.put("userId", userId);
            addMap.put("likedPlaces", activity.getLikedPlaces());

            userlikesFirestoreService.addDocument(addMap);
        }


        return new ValidationResult();
    }

    public ValidationResult addToVisits(Activity activity) throws Exception{
        String userId = activity.getUserId();

        Query userDocumentExistsQuery = uservisitsFirestoreService
                .getRef()
                .whereEqualTo("userId", userId);

        boolean userDocumentExists = !uservisitsFirestoreService.executeQuery(userDocumentExistsQuery).isEmpty();

        if (userDocumentExists){
            System.out.println("Found Document");
            DocumentSnapshot userDocument = uservisitsFirestoreService.executeQuery(userDocumentExistsQuery).get(0);
            String documentId = userDocument.getId();

            System.out.println(documentId);

            DocumentReference docRef = uservisitsFirestoreService.getRef().document(documentId);

            Map<String, Object> updateMap = new HashMap<>();
            updateMap.put("likedPlaces", activity.getLikedPlaces());

            docRef.set(updateMap);

        } else {
            Map <String, Object> addMap = new HashMap<>();

            System.out.println("attempting to add activity");
            System.out.println(activity);

            addMap.put("userId", userId);
            addMap.put("likedPlaces", activity.getLikedPlaces());

            uservisitsFirestoreService.addDocument(addMap);
        }


        return new ValidationResult();
    }

}
