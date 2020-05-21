package com.example.easyfood.db;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.Product;


public interface IDatabase {
    // TODO I don't think that we need the three first methods here for the course implementation. add/remove/update.
    void addEatery(Eatery eatery);
    void removeEatery(Eatery eatery);
    void updateEatery(Eatery eatery);
    void getAllEateries(IEateriesCallback callback);

    void addProduct(String eateryId, Product product);
    void removeProduct(String eateryId, String productId);
    void updateProduct(String eateryId, String productId, Product product);
    void getAllProducts(String eateryId, IProductsCallback callback); // should probably add Eatery object here
}
