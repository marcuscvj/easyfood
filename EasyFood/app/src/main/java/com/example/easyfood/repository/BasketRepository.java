package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;
import com.example.easyfood.model.TotalPriceCalculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a BasketRepository
 */
public class BasketRepository {
    private static BasketRepository instance;
    private Order order;
    private ArrayList<Product> productsList = new ArrayList<>();
    private MutableLiveData<List<Product>> products= new MutableLiveData<>();
    private TotalPriceCalculator calculator = new TotalPriceCalculator();

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

    /**
     * Add a product
     *
     * @param product : Product
     */
    public void addProduct(Product product) {
        products.getValue().add(product);
    }

    /**
     * Set an order
     *
     * @param eateryId : String
     * @param eateryName : String
     */
    public void setOrder(String eateryId, String eateryName) {
        if (order == null) {
            order = new Order(eateryId);
            order.setEateryName(eateryName);
        }
    }

    /**
     * Returns the id of eatery from an order
     *
     * @return total : String
     */
    public String getEateryIdFromOrder() {
        return order.getEateryId();
    }

    /**
     * Removes a product
     *
     * @param product : Product
     */
    public void removeProduct(Product product) {
        products.getValue().remove(product);

        //Resets order if the basket is empty.
        resetOrder();
    }

    /**
     * Gets an order
     *
     * @return order : Order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Update the product list
     */
    public void updateProductList() {
        productsList = new ArrayList<>();
    }

    /**
     * Returns the total sum of all products
     *
     * @return sum : double - The total sum of all products
     */
    public double getTotalSumOfProducts() {
        return calculator.getTotalPriceOfProducts(products.getValue());
    }

    /**
     * Reset the order
     */
    private void resetOrder() {
        if (products.getValue().size() == 0) {
            this.order = null;
        }
    }
}
