package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Product;
import com.example.easyfood.repository.ProductRepository;

import java.util.List;

/**
 * Represents a ViewModel of ManagerMenuActivity
 */
public class ManagerMenuViewModel extends ViewModel {
    private ProductRepository productRepository;
    private MutableLiveData<List<Product>> products;

    /**
     * Initializes the ViewModel
     *
     * @param eateryId : String - The id of the eatery
     */
    public void init(String eateryId){
        if (products != null) {
            return;
        }
        productRepository = ProductRepository.getInstance();
        products = productRepository.getProducts(eateryId);
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
     * @param category : String - The category of the product
     */
    public void createProduct(String eateryId, String name, String description,
                              Double price, String category) {
        productRepository.createProductAndAddToDatabase(eateryId, name, description,
                price, category);
    }

    /**
     * Removes a product
     *
     * @param eateryId : String - The id of the eatery
     * @param productId : String - The id of the product
     */
    public void removeProduct(String eateryId, String productId) {
        productRepository.removeProduct(eateryId, productId);
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
     * @param category : String - The category of the product
     */
    public void updateProduct(String eateryId, String productId, String name,
                              String description, Double price, String category) {
        productRepository.updateProduct(eateryId, productId, name, description, price, category);
    }

}
