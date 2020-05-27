package com.example.easyfood.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Product;
import com.example.easyfood.repository.BasketRepository;
import com.example.easyfood.repository.ProductRepository;

import java.util.List;

/**
 * Represents the ViewModel of CustomerMenuActivity
 */
public class CustomerMenuViewModel extends ViewModel {
    private MutableLiveData<List<Product>> productsInMenu;
    private BasketRepository basketRepository;
    private ProductRepository productsRepository;
    private String eateryId;
    private String eateryName;


    /**
     * Initializes the ViewModel
     *
     * @param eateryId : String - The id of the eatery
     * @param eateryName : String - The name of the eatery
     */
    public void init(String eateryId, String eateryName){
        if(productsInMenu != null) {
            return;
        }

        this.eateryId = eateryId;
        this.eateryName = eateryName;
        productsRepository= ProductRepository.getInstance();
        basketRepository = BasketRepository.getInstance();
        basketRepository.getProducts();
        productsInMenu = productsRepository.getProducts(eateryId);

    }

    /**
     * Returns the products.
     *
     * @return LiveData: products - The products.
     */
    public LiveData<List<Product>> getProductsInMenu() {
        return productsInMenu;
    }

    public void addProduct(int position, Context context) {

        Product product = getProductsInMenu().getValue().get(position);
        basketRepository.setOrder(eateryId, eateryName);

        if (eateryId == basketRepository.getEateryIdFromOrder()) {
            basketRepository.addProduct(product);
        } else {
            CharSequence text = "You can only order from one restaurant at a time!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }



    }


}
