package com.example.easyfood.db;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.easyfood.model.Eatery;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
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
        // Add a new document with a generated ID
        // Check here if the eatery already exists or not in db.collection

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
     * Gets a eatery document from the eateries collection.
     */
    public Eatery getEatery(Eatery eatery) {
//        DocumentReference docRef = db.collection("eateries").document(eatery.getId());
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(EATERY_TAG, "DocumentSnapshot data: " + document.getData());
//                        return new Eatery();
//                    } else {
//                        Log.d(EATERY_TAG, "No such document");
//                    }
//                } else {
//                    Log.d(EATERY_TAG, "get failed with ", task.getException());
//                }
//            }
//        });
        return new Eatery("Test", "Test");
    }

    @Override
    public ArrayList<Eatery> getAllEateries(final ICallback callback) {
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

                            callback.eateriesCallback(eateries);
                        } else {
                            Log.d(EATERY_TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        return eateryList;
    }

    @Override
    public void removeEatery(Eatery eatery) {

    }

    @Override
    public void updateEatery(Eatery eatery) {

    }

    private void processData(ArrayList<Eatery> list) {
        eateryList.addAll(list);
    }
}
