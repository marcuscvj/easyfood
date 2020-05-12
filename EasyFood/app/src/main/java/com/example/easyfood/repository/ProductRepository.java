package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.db.IProductsCallback;
import com.example.easyfood.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static ProductRepository instance;
    private static Firebase fb;
    private ArrayList<Product> productList;
    private MutableLiveData<List<Product>> products = new MutableLiveData<>();

    /**
     * Returns an instance of the ProductRepository
     *
     * @return ProductRepository: instance - The instance of the ProductRepository.
     */
    public static ProductRepository getInstance() {
        if (instance == null){
            instance = new ProductRepository();
            fb = new Firebase();
        }
        return instance;
    }

    public MutableLiveData<List<Product>> getProducts(String ID) {
        // Empty the list so we don't get duplicates
        productList = new ArrayList<>();

        fb.getAllProducts(ID, new IProductsCallback() {
            @Override
            public void send(ArrayList<Product> list) {
            productList.addAll(list);
            products.setValue(productList);
            }

        });

        products.setValue(productList);
        return products;
    }
}
