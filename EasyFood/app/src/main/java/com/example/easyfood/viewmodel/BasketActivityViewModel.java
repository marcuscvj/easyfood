package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Product;
import com.example.easyfood.repository.BasketRepository;


import java.util.List;

public class BasketActivityViewModel extends ViewModel {

    private MutableLiveData<List<Product>> products;
    private BasketRepository basketRepository;

    /**
     * Initializes the ViewModel
     */
    public void init(){
        if(products != null) {
            return;
        }
        basketRepository= BasketRepository.getInstance();
        products = basketRepository.getProducts();
    }

    /**
     * Returns the products.
     *
     * @return LiveData: products - The products.
     */
    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void removeProduct(Product product) {
        products.getValue().remove(product);
        products.setValue(products.getValue());
    }



}
