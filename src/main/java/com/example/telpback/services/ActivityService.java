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


    private interface BatchExecutor {
        void execute();
    }

    private static class BatchSetDocuments<T extends Activity.ActivityMethods> implements BatchExecutor {
        private BatchSetDocuments(FirestoreService<ActivityDTO> service, List<T> activityList, String userId) {
            this.service = service;
            this.activityList = activityList;
            this.userId = userId;
        }

        private FirestoreService<ActivityDTO> service;
        private List<T> activityList;
        private String userId;

        @Override
        public void execute() {
            WriteBatch batch = service.getRef().getFirestore().batch();

            for (T current : activityList) {
                String placeId = current.getPlaceId();
                ActivityDTO likedRelationship = new ActivityDTO(userId, placeId);
                DocumentReference doc = service.getRef().document();

                batch.set(doc, likedRelationship);
            }

            batch.commit();
        }
    }

    private static class BatchDeleteDocuments<T extends Activity.ActivityMethods> implements BatchExecutor {
        private BatchDeleteDocuments(FirestoreService<ActivityDTO> service, List<T> activityList, String userId) {
            this.service = service;
            this.activityList = activityList;
            this.userId = userId;
        }

        private FirestoreService<ActivityDTO> service;
        private List<T> activityList;
        private String userId;

        @Override
        public void execute() {
            WriteBatch batch = service.getRef().getFirestore().batch();

            for (T current : activityList) {
                String placeId = current.getPlaceId();

                Query getRelationshipQuery = service.getRef()
                        .whereEqualTo("userId", userId)
                        .whereEqualTo("placeId", placeId)
                        .limit(1);

                List<QueryDocumentSnapshot> snapshots = service.executeQuery(getRelationshipQuery);

                if (!snapshots.isEmpty()) {
                    for (DocumentSnapshot snapshot : snapshots) {
                        DocumentReference docRef = snapshot.getReference();
                        batch.delete(docRef);
                    }
                }
            }
            batch.commit();
        }

    }

    public ValidationResult addToLikes(Activity activity) {
        String userId = activity.getUserId();
        List<Activity.LikedPlace> likedPlaces = activity.getLikedPlaces();

        BatchExecutor batchSetDocuments = new BatchSetDocuments<>(
                userlikesFirestoreService, likedPlaces, userId);
        batchSetDocuments.execute();

        return new ValidationResult(false, HttpStatus.CREATED, "batch set successful.");
    }

    public ValidationResult removeFromLikes(Activity activity) {
        String userId = activity.getUserId();
        List<Activity.LikedPlace> likedPlaces = activity.getLikedPlaces();

        BatchExecutor batchDeleteDocuments = new BatchDeleteDocuments<>(
                userlikesFirestoreService, likedPlaces, userId);
        batchDeleteDocuments.execute();

        return new ValidationResult(false, HttpStatus.NO_CONTENT, "Successfully deleted documents");
    }

    public ValidationResult addToVisits(Activity activity) {
        String userId = activity.getUserId();
        List<Activity.VisitedPlace> visitedPlaces = activity.getVisitedPlaces();

        BatchExecutor batchSetDocuments = new BatchSetDocuments<>(
                uservisitsFirestoreService, visitedPlaces, userId);
        batchSetDocuments.execute();

        return new ValidationResult(false, HttpStatus.CREATED, "batch set successful.");
    }

    public ValidationResult removeFromVisits(Activity activity) {
        String userId = activity.getUserId();
        List<Activity.VisitedPlace> visitedPlaces = activity.getVisitedPlaces();

        BatchExecutor batchDeleteDocuments = new BatchDeleteDocuments<>(
                uservisitsFirestoreService, visitedPlaces, userId);
        batchDeleteDocuments.execute();

        return new ValidationResult(false, HttpStatus.NO_CONTENT, "Successfully deleted documents");
    }

}
