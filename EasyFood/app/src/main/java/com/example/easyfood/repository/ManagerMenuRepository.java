package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.db.IProductsCallback;
import com.example.easyfood.model.Product;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ManagerMenuRepository {
    private static ManagerMenuRepository instance;
    private static Firebase firebase;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private ArrayList<Product> productList;
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    /**
     * Returns an instance of the ManagerMenuRepository
     *
     * @return ManagerMenuRepository: instance - The instance of the ManagerMenuRepository.
     */
    public static ManagerMenuRepository getInstance() {
        if (instance == null){
            instance = new ManagerMenuRepository();
            firebase = new Firebase();
        }

        return instance;
    }

    public MutableLiveData<List<Product>> getAllProducts(String ID) {
        // Empty the list so we don't get duplicates
        updateLiveData(ID);
        return products;
    }

    /**
     * Creates a new product and adds it to the database
     *
     * @param eateryId : String - The id of the eatery
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     */
    public void createProductAndAddToDatabase(String eateryId, String name, String description, Double price) {
        String id = getGeneratedProductIdFromDatabase();

        Product product = new Product(name, description, price, id);

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
    private String getGeneratedProductIdFromDatabase() {
        return database.collection("eateries").document().collection("products").document().getId();
    }
}
