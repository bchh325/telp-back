package com.example.telpback.services;

import com.example.telpback.dto.ActivityDTO;
import com.example.telpback.generics.FirestoreService;
import com.example.telpback.models.Activity;
import com.example.telpback.models.ValidationResult;
import com.google.cloud.firestore.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final FirestoreService<ActivityDTO> userlikesFirestoreService;
    private final FirestoreService<ActivityDTO> uservisitsFirestoreService;

    public ActivityService(FirestoreService<ActivityDTO> userlikesFirestoreService,
                           FirestoreService<ActivityDTO> uservisitsFirestoreService) {
        this.userlikesFirestoreService = userlikesFirestoreService;
        this.uservisitsFirestoreService = uservisitsFirestoreService;
    }

    public ValidationResult addToLikes(Activity activity) throws Exception {
        String userId = activity.getUserId();
        List<Activity.LikedPlace> likedPlaces = activity.getLikedPlaces();

        WriteBatch batchSet = userlikesFirestoreService.getRef().getFirestore().batch();

        for (Activity.LikedPlace current : likedPlaces) {
            String placeId = current.getPlaceId();
            ActivityDTO likedRelationship = new ActivityDTO(userId, placeId);
            DocumentReference doc = userlikesFirestoreService.getRef().document();

            batchSet.set(doc, likedRelationship);
        }

        batchSet.commit();

        return new ValidationResult(false, HttpStatus.CREATED, "batch set successful.");
    }

    public ValidationResult addToVisits(Activity activity) throws Exception {
        String userId = activity.getUserId();
        List<Activity.VisitedPlace> visitedPlaces = activity.getVisitedPlaces();

        WriteBatch batchSet = uservisitsFirestoreService.getRef().getFirestore().batch();

        for (Activity.VisitedPlace current : visitedPlaces) {
            String placeId = current.getPlaceId();
            ActivityDTO visitedRelationship = new ActivityDTO(userId, placeId);
            DocumentReference doc = uservisitsFirestoreService.getRef().document();

            batchSet.set(doc, visitedRelationship);
        }

        batchSet.commit();

        return new ValidationResult(false, HttpStatus.CREATED, "batch set successful.");
    }

    public ValidationResult removeFromLikes(Activity activity) throws Exception {
        String userId = activity.getUserId();
        List<Activity.LikedPlace> likedPlaces = activity.getLikedPlaces();


        WriteBatch batchDelete = userlikesFirestoreService.getRef().getFirestore().batch();

        for (Activity.LikedPlace current : likedPlaces) {
            String placeId = current.getPlaceId();

            Query getRelationshipQuery = userlikesFirestoreService.getRef()
                    .whereEqualTo("userId", userId)
                    .whereEqualTo("placeId", placeId)
                    .limit(1);

            List<QueryDocumentSnapshot> snapshots = userlikesFirestoreService.executeQuery(getRelationshipQuery);

            if (!snapshots.isEmpty()) {
                for (DocumentSnapshot snapshot : snapshots) {
                    DocumentReference docRef = snapshot.getReference();
                    batchDelete.delete(docRef);
                }
            }
        }

        batchDelete.commit();
        return new ValidationResult(false, HttpStatus.NO_CONTENT, "Successfully deleted documents");
    }

    public ValidationResult removeFromVisits(Activity activity) throws Exception {
        String userId = activity.getUserId();
        List<Activity.VisitedPlace> visitedPlaces = activity.getVisitedPlaces();

        WriteBatch batchDelete = uservisitsFirestoreService.getRef().getFirestore().batch();

        for (Activity.VisitedPlace current : visitedPlaces) {
            String placeId = current.getPlaceId();

            Query getRelationshipQuery = uservisitsFirestoreService.getRef()
                    .whereEqualTo("userId", userId)
                    .whereEqualTo("placeId", placeId)
                    .limit(1);

            List<QueryDocumentSnapshot> snapshots = uservisitsFirestoreService.executeQuery(getRelationshipQuery);

            if (!snapshots.isEmpty()) {
                for (DocumentSnapshot snapshot : snapshots) {
                    DocumentReference docRef = snapshot.getReference();
                    batchDelete.delete(docRef);
                }
            }
        }

        batchDelete.commit();
        return new ValidationResult(false, HttpStatus.NO_CONTENT, "Successfully deleted documents");
    }

}
