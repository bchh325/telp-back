package com.example.telpback.services;

import com.example.telpback.generics.BaseFirestoreService;
import com.example.telpback.models.Follower;
import com.example.telpback.models.ValidationResult;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    private final BaseFirestoreService<Follower> followerService;

    public FollowerService(BaseFirestoreService<Follower> followerBaseFirestoreService) {
        this.followerService = followerBaseFirestoreService;
    }

    public ValidationResult followUser(Follower followObject) throws Exception {
        String followerId = followObject.getFollowerId();
        String followeeId = followObject.getFolloweeId();

        Query relationshipExistsQuery = followerService.getRef()
                .whereEqualTo("followerId", followerId)
                .whereEqualTo("followeeId", followeeId)
                .limit(1);
        boolean relationshipExists = !followerService.executeQuery(relationshipExistsQuery).isEmpty();
        if (relationshipExists) {
            return new ValidationResult(true,
                    HttpStatus.CONFLICT,
                    "Follower relationship between " + followerId + "and " + followeeId + " already exists.");
        }

        boolean followerAddedSuccess = followerService.addDocument(followObject);
        if (followerAddedSuccess) {
            return new ValidationResult(false, HttpStatus.CREATED, "Successfully added follower");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error adding follower document");
        }
    }

    public ValidationResult unfollowUser(Follower followObject) throws Exception {
        String followerId = followObject.getFollowerId();
        String followeeId = followObject.getFolloweeId();

        Query getRelationshipQuery = followerService.getRef()
                .whereEqualTo("followerId", followerId)
                .whereEqualTo("followeeId", followeeId)
                .limit(1);

        List<QueryDocumentSnapshot> snapshots = followerService.executeQuery(getRelationshipQuery);

        boolean relationshipExists = !snapshots.isEmpty();
        if (!relationshipExists) {
            return new ValidationResult(true, HttpStatus.NOT_FOUND, "Follower relationship does not exist.");
        }

        DocumentReference docRef = snapshots.get(0).getReference();
        boolean deleteSuccessful = followerService.deleteDocument(docRef);
        if (deleteSuccessful) {
            return new ValidationResult(false, HttpStatus.OK, "Successfully deleted follower relationship");
        } else {
            return new ValidationResult(true, HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting follower relationship");
        }
    }
}
