package com.example.easyfood.repository;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.model.Product;
import com.google.firebase.firestore.FirebaseFirestore;

public class ManagerMenuRepository {
    private static ManagerMenuRepository instance;
    private static Firebase firebase;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

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
     * Returns a new generated product id from the database
     *
     * @return String: id - New id from the database
     */
    private String getGeneratedProductIdFromDatabase() {
        return database.collection("eateries").document().collection("products").document().getId();
    }
}
