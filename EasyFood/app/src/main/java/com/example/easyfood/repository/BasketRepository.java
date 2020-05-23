package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a BasketRepository
 */
public class BasketRepository {
    private static BasketRepository instance;
    private Order order;
    private ArrayList<Product> productsList = new ArrayList<>();
    private  MutableLiveData<List<Product>> products= new MutableLiveData<>();

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
        products.setValue(productsList);
        return products;
    }


    public void addProduct(Product product) {
        products.getValue().add(product);
    }

    public void setOrder(String eateryId) {
        if (order == null) {
            order = new Order(eateryId);
        }
    }

    public String getEateryIdFromOrder() {
        return order.getEateryId();
    }

    public void resetOrder() {
        if (products.getValue().size() == 0) {
            this.order = null;
        }
    }

    public Order getOrder() {
        return order;
    }



}
