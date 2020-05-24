package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.db.IProductsCallback;
import com.example.easyfood.model.Product;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ProductRepository Singleton
 */
public class ProductRepository {
    private static ProductRepository instance;
    private static Firebase firebase;
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
            firebase = new Firebase();
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
    public void createProductAndAddToDatabase(String eateryId, String name, String description, Double price, String category) {
        String id = getGeneratedProductIdFromDatabase(eateryId);

        Product product = new Product(name, description, price, id, category);

        firebase.addProduct(eateryId, product);

    }

    /**
     * Removes the product from the database
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     */
    public void removeProduct(String eateryId, String productId) {
        firebase.removeProduct(eateryId, productId);
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
    public void updateProduct(String eateryId, String productId, String name, String description, Double price, String category) {
        Product product = new Product(name, description, price, productId, category);
        firebase.updateProduct(eateryId, productId, product);
    }

    /**
     * Updates the LiveData<List>
     *
     * @param eateryId : String - The id of the eatery
     */
    private void updateLiveData(String eateryId) {
        productList = new ArrayList<>();

        firebase.getAllProducts(eateryId, new IProductsCallback() {
            @Override
            public void send(ArrayList<Product> list) {
                productList.addAll(list);
                products.setValue(list);
            }

        });

        products.setValue(productList);
    }

    /**
     * Returns a new generated product id from the database
     *
     * @return String: id - New id from the database
     */
    private String getGeneratedProductIdFromDatabase(String eateryId) {
        return database.collection("eateries").document(eateryId).collection("products").document().getId();
    }
}
