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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Firebase class
 */
public class Firebase implements IDatabase {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String EATERY_TAG = "EATERY";
    private ArrayList<Eatery> eateryList = new ArrayList<>();

    /**
     * Adds a new eatery document to the eateries collection.
     */
    public void addEatery(Eatery eatery) {
        db.collection("eateries").document(eatery.getId())
                .set(eatery)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(EATERY_TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(EATERY_TAG, "Error writing document", e);
                    }
                });
    }

    /**
     * Returns all eateries objects from the database.
     *
     * @return ArrayList<Eatery>: eateryList
     */
    @Override
    public ArrayList<Eatery> getAllEateries(final IEateriesCallback callback) {
        db.collection("eateries")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Eatery> eateries = new ArrayList<>();

                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                String name = document.getString("name");
                                String id = document.getString("id");
                                eateries.add(new Eatery(name, id));
                            }

                            callback.send(eateries);
                        } else {
                            Log.d(EATERY_TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return eateryList;
    }

    /**
     * Removes an Eatery object on the database.
     */
    @Override
    public void removeEatery(Eatery eatery) {
        db.collection("eateries").document(eatery.getId()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(EATERY_TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(EATERY_TAG, "Error with deletion of document", e);
                    }
                });
    }

    /**
     * Updates an Eatery object on the database.
     */
    @Override
    public void updateEatery(Eatery eatery) {
        db.collection("eateries").document(eatery.getId())
                .set(eatery)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(EATERY_TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(EATERY_TAG, "Error with deletion of document", e);
                    }
                });
    }
}
