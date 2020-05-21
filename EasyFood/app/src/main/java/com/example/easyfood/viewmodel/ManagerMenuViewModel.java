package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Product;
import com.example.easyfood.repository.ManagerMenuRepository;

import java.util.List;

public class ManagerMenuViewModel extends ViewModel {
    private ManagerMenuRepository managerMenuRepository;
    private MutableLiveData<List<Product>> products;

    /**
     * Initializes the ViewModel
     */
    public void init(String restaurantID){
        if(products != null) {
            return;
        }
        managerMenuRepository = ManagerMenuRepository.getInstance();
        products = managerMenuRepository.getAllProducts(restaurantID);
    }

    /**
     * Returns the products.
     *
     * @return LiveData: products - The products.
     */
    public LiveData<List<Product>> getAllProducts() {
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
    public void createProduct(String eateryId, String name, String description, Double price) {
        managerMenuRepository.createProductAndAddToDatabase(eateryId, name, description, price);
    }

    /**
     * Removes a product
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     */
    public void removeProduct(String eateryId, String productId) {
        managerMenuRepository.removeProduct(eateryId, productId);
    }

    /**
     *
     * Updates a product
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     */
    public void updateProduct(String eateryId, String productId, String name, String description, Double price) {
        managerMenuRepository.updateProduct(eateryId, productId, name, description, price);
    }

}
