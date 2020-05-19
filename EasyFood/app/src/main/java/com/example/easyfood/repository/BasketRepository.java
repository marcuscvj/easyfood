package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BasketRepository {
    private static BasketRepository instance;
    public ArrayList<Product> productsList = new ArrayList<Product>();
    private MutableLiveData<List<Product>> products= new MutableLiveData<>();

    /**
     * Returns an instance of the EateryRepository
     *
     * @return EateryRepository: instance - The instance of the EateryRepository.
     */
    public static BasketRepository getInstance() {
        if (instance == null){
            instance = new BasketRepository();
        }
        return instance;
    }

    /**
     * Returns the eateries.
     *
     * @return MutableLiveData<List<Eatery>>: eateries - The eateries.
     */
    public MutableLiveData<List<Product>> getProducts() {
        Product hej = new Product("hej", "hej", 77.0, "hej");
        productsList.add(hej);
        products.setValue(productsList);
        return products;
    }
}
