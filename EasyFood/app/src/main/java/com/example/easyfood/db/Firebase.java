package com.example.easyfood.db;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
                                String id = document.getId();
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
    public void addProduct(String eateryId, Product product) {
        db.collection("eateries").document(eateryId).collection("products").document(product.getId())
                .set(product)
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

    @Override
    public void removeProduct(String eateryId, String productId) {
        db.collection("eateries").document(eateryId).collection("products").document(productId).delete()
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

    @Override
    public void updateProduct(String eateryId, String productId, Product product) {
        db.collection("eateries").document(eateryId).collection("products").document(product.getId())
                .set(product)
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

    @Override
    public void getAllProducts(String eateryId, final IProductsCallback callback) {
        db.collection("eateries").document(eateryId).collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Product> products = new ArrayList<>();
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                String name = document.getString("name");
                                String desc = document.getString("description");
                                Double price = document.getDouble("price");
                                String id = document.getId();
                                products.add(new Product(name, desc, price, id));
                            }

                            callback.send(products);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
