package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Product;
import com.example.easyfood.repository.BasketRepository;
import com.example.easyfood.repository.ProductRepository;

import java.util.List;

public class CustomerMenuViewModel extends ViewModel {

    private MutableLiveData<List<Product>> productsInMenu;
    private MutableLiveData<List<Product>> productsInBasket;
    private BasketRepository basketRepository;
    private ProductRepository productsRepository;


    /**
     * Initializes the ViewModel
     */
    public void init(String restaurantID){
        if(productsInMenu != null) {
            return;
        }
        productsRepository= ProductRepository.getInstance();
        basketRepository = BasketRepository.getInstance();
        productsInBasket = basketRepository.getProducts();
        productsInMenu = productsRepository.getProducts(restaurantID);

    }

    /**
     * Returns the products.
     *
     * @return LiveData: products - The products.
     */
    public LiveData<List<Product>> getProductsInMenu() {
        return productsInMenu;
    }

    public void addProduct(Product product) {
        productsInBasket.getValue().add(product);
    }


}
