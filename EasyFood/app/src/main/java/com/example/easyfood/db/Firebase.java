package com.example.easyfood.db;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.easyfood.model.Eatery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * Firebase class
 */
public class Firebase {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String EATERY_TAG = "EATERY";

    /**
     * Adds a new eatery document to the eateries collection.
     */
    public void addEatery(Eatery eatery) {
        // Add a new document with a generated ID
        db.collection("eateries")
                .add(eatery)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(EATERY_TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(EATERY_TAG, "Error adding document", e);
                    }
                });
    }

    /**
     * Gets a eatery document from the eateries collection.
     */
    public void getEatery(Eatery eatery) {

        // Needs to do a query below and find the right one with that id.

        DocumentReference docRef = db.collection("eateries").document(eatery.getId());

        Log.d("Test: ", eatery.getId());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(EATERY_TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(EATERY_TAG, "No such document");
                    }
                } else {
                    Log.d(EATERY_TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    public void addMenuItem() {
        // should probably take a model like "MenuItem"
    }
}
