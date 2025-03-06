package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Follower;
import com.example.telpback.models.ValidationResult;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    private final BaseFirestoreService<Follower> followerBaseFirestoreService;

    public FollowerService(BaseFirestoreService<Follower> followerBaseFirestoreService) {
        this.followerBaseFirestoreService = followerBaseFirestoreService;
    }

    public ValidationResult followUser(Follower followObject) throws Exception {
        String followerId = followObject.getFollowerId();
        String followeeId = followObject.getFolloweeId();

        Query relationshipExistsQuery = followerBaseFirestoreService.getRef()
                .whereEqualTo("followerId", followerId)
                .whereEqualTo("followeeId", followeeId)
                .limit(1);
        boolean relationshipExists = !followerBaseFirestoreService.executeQuery(relationshipExistsQuery).isEmpty();
        if (relationshipExists) {
            return new ValidationResult(true,
                    HttpStatus.CONFLICT,
                    "Follower relationship between " + followerId + "and " + followeeId + " already exists.");
        }

        boolean followerAddedSuccess = followerBaseFirestoreService.addDocument(followObject);
        if (followerAddedSuccess) {
            return new ValidationResult(false, HttpStatus.CREATED, "Successfully added follower");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error adding follower document");
        }
    }

    public ValidationResult unfollowUser(Follower followObject) throws Exception {
        String followerId = followObject.getFollowerId();
        String followeeId = followObject.getFolloweeId();

        Query getRelationshipQuery = followerBaseFirestoreService.getRef()
                .whereEqualTo("followerId", followerId)
                .whereEqualTo("followeeId", followeeId)
                .limit(1);

        List<QueryDocumentSnapshot> snapshots = followerBaseFirestoreService.executeQuery(getRelationshipQuery);

        boolean relationshipExists = !snapshots.isEmpty();
        if (!relationshipExists) {
            return new ValidationResult(true, HttpStatus.NOT_FOUND, "Follower relationship does not exist.");
        }

        DocumentReference docRef = snapshots.get(0).getReference();
        boolean deleteSuccessful = followerBaseFirestoreService.deleteDocument(docRef);
        if (deleteSuccessful) {
            return new ValidationResult(false, HttpStatus.OK, "Successfully deleted follower relationship");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting follower relationship");
        }
    }
}
