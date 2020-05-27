package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;
import com.example.easyfood.repository.BasketRepository;
import com.example.easyfood.repository.OrderRepository;
import com.google.firebase.auth.FirebaseAuth;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the ViewModel of BasketActivity
 */
public class BasketViewModel extends ViewModel {
    private MutableLiveData<List<Product>> products;
    private BasketRepository basketRepository;
    private OrderRepository orderRepository;

    /**
     * Initializes the ViewModel
     */
    public void init(){
        if(products != null) {
            return;
        }

        basketRepository= BasketRepository.getInstance();
        orderRepository = OrderRepository.getInstance();
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

    /**
     * Removes a product
     *
     * @param position : int - The position of the product
     */
    public void removeProduct(int position) {
        basketRepository.removeProduct(products.getValue().get(position));
        products = basketRepository.getProducts();
    }

    /**
     * Sends an order
     *
     * @param sum : String - The total sum of the order
     * @param note : String - The note to the eatery
     */
    public void sendOrder(String sum, String note) {
        Order order = basketRepository.getOrder();

        SecureRandom random = new SecureRandom();
        int orderNumber= random.nextInt(100000);
        String formatted = String.format("%05d", orderNumber);
        orderNumber = Integer.parseInt(formatted);
        order.setOrderNumber(orderNumber);

        ArrayList<Product> productsToBeSent = (ArrayList<Product>) basketRepository.getProducts().getValue();
        order.setProducts(productsToBeSent);

        order.setSum(Double.parseDouble(sum));

        order.setMessage(note);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        order.setCustomerId(userID);

        orderRepository.sendOrder(order);

        updateProducts();
    }

    /**
     * Updates the product list
     */
    private void updateProducts() {
        basketRepository.updateProductList();
        products = basketRepository.getProducts();
    }



}
