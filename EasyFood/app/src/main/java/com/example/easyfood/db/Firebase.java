package com.example.easyfood.db;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


/**
 * Firebase class
 */
public class Firebase implements IDatabase {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String TAG = "FIREBASE";

    /**
     * Adds a new eatery document to the eateries collection.
     */
    public void addEatery(Eatery eatery) {
        db.collection("eateries").document(eatery.getId())
                .set(eatery)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
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
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error with deletion of document", e);
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
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error with deletion of document", e);
                    }
                });
    }

    /**
     * Gets all eateries objects from the database.
     */
    @Override
    public void getAllEateries(final IEateriesCallback callback) {
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
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    public void addProduct(Eatery eatery, Product product) {

    }

    @Override
    public void removeProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void getAllProducts(String eateryId, final IProductsCallback callback) {
        db.collection("eateries/${eateryId}/products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Product> products = new ArrayList<>();

                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Map<String, Object> map = document.getData();

                                // Getting the products from the document array field
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    if (entry.getKey().equals("products")) {
                                        // Log.d("TAG", entry.getValue().toString());

                                        products.add(new Eatery(name, id));

                                        // products.add(new Product())
                                    }
                                }
                            }

                            callback.send(products);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
