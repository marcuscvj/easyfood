package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.easyfood.model.Product;
import com.example.easyfood.repository.ProductRepository;

import java.util.List;

public class ProductActivityViewModel extends ViewModel {

    private MutableLiveData<List<Product>> products;
    private ProductRepository productsRepository;

    /**
     * Initializes the ViewModel
     */
    public void init(String restaurantID){
        if(products != null) {
            return;
        }
        productsRepository= ProductRepository.getInstance();
        products = productsRepository.getProducts(restaurantID);
    }

    /**
     * Returns the products.
     *
     * @return LiveData: products - The products.
     */
    public LiveData<List<Product>> getProducts() {
        return products;
    }
}
