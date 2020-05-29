package com.example.easyfood.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a ProductRepository Singleton
 */
public class ProductRepository {
    private String TAG = "ProductRepository";
    private static ProductRepository instance;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private ArrayList<Product> productList;
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    /**
     * Returns an instance of the ProductRepository
     *
     * @return ProductRepository: instance - The instance of the ProductRepository.
     */
    public static ProductRepository getInstance() {
        if (instance == null){
            instance = new ProductRepository();
        }
        return instance;
    }

    /**
     * Returns a LiveData list of the products belonging to a specific eatery
     *
     * @param eateryId : String - The id of the eatery
     * @return products : MutableLiveData<List<Product>> - The list of products
     */
    public MutableLiveData<List<Product>> getProducts(String eateryId) {
        updateLiveData(eateryId);
        return products;
    }

    /**
     * Creates a new product and adds it to the database
     *
     * @param eateryId : String - The id of the eatery
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     * @param category : String - The category of the product
     */
    public void createProductAndAddToDatabase(String eateryId, String name, String description,
                                              Double price, String category) {
        String id = getGeneratedProductIdFromDatabase(eateryId);
        Product product = new Product(name, description, price, id, category);

        addProductToDatabase(eateryId, product);
    }

    /**
     * Removes the product from the database
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     */
    public void removeProduct(String eateryId, String productId) {
        removeProductFromDatabase(eateryId, productId);
        updateLiveData(eateryId);
    }

    /**
     * Updates the product
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     * @param category : String - The category of the product
     */
    public void updateProduct(String eateryId, String productId, String name, String description,
                              Double price, String category) {
        Product product = new Product(name, description, price, productId, category);
        updateProductInDatabase(eateryId, product);
    }

    /**
     * Updates the LiveData<List>
     *
     * @param eateryId : String - The id of the eatery
     */
    private void updateLiveData(String eateryId) {
        productList = new ArrayList<>();

        getAllProductsFromDatabase(eateryId, new IProductsCallback() {
            @Override
            public void send(ArrayList<Product> list) {
                productList.addAll(list);
                products.setValue(list);
            }

        });

        products.setValue(productList);
    }

    /**
     * Gets all the products that belongs to a specific eatery from the database
     *
     * @param eateryId : String - The id of the eatery
     * @param callback : IProductsCallback - Callback
     */
    private void getAllProductsFromDatabase(String eateryId, final IProductsCallback callback) {
        database.collection("eateries").document(eateryId).collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Product> products = new ArrayList<>();
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Product product = document.toObject(Product.class);
                                products.add(product);
                            }

                            callback.send(products);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    /**
     * Adds a product to an eatery in the database
     *
     * @param eateryId : String - The id of the eatery
     * @param product : Product - The product
     */
    private void addProductToDatabase(String eateryId, Product product) {
        database.collection("eateries").document(eateryId).collection("products").document(product.getId())
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

    /**
     * Removes a product from an eatery in the database
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     */
    private void removeProductFromDatabase(String eateryId, String productId) {
        database.collection("eateries").document(eateryId).collection("products").document(productId).delete()
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
     * Updates a product in the database
     *
     * @param eateryId : String - The id of the eatery
     * @param product : Product - The product
     */
    private void updateProductInDatabase(String eateryId, Product product) {
        database.collection("eateries").document(eateryId).collection("products").document(product.getId())
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

    /**
     * Returns a new generated product id from the database
     *
     * @return String: id - New id from the database
     */
    private String getGeneratedProductIdFromDatabase(String eateryId) {
        return database.collection("eateries")
                .document(eateryId)
                .collection("products")
                .document().getId();
    }

    /**
     * Interface
     */
    public interface IProductsCallback {
        void send(ArrayList<Product> list);
    }
}
