package com.example.easyfood.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.model.OrderPaymentMethodEnums;
import com.example.easyfood.model.OrderStatusEnums;
import com.example.easyfood.model.Product;
import com.example.easyfood.repository.BasketRepository;
import com.example.easyfood.repository.OrderRepository;
import com.google.firebase.auth.FirebaseAuth;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

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

    public void removeProduct(Product product) {
        products.getValue().remove(product);
        products.setValue(products.getValue());
        //Resets order if the basket is empty.
        basketRepository.resetOrder();

    }

    public void sendOrder(String sum) {
        Order order = basketRepository.getOrder();

        SecureRandom random = new SecureRandom();
        long orderNumber= random.nextInt(100000);
        String formatted = String.format("%05d", orderNumber);
        long ordernumber = Long.parseLong(formatted);
        order.setOrderNumber(ordernumber);

        ArrayList<Product> products = (ArrayList<Product>) basketRepository.getProducts().getValue();
        order.setProducts(products);

        order.setPaymentMethod(OrderPaymentMethodEnums.CASH);

        order.setPaid(false);

        order.setOrderStatus(OrderStatusEnums.CREATED);

        order.setSum(Double.parseDouble(sum));


        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        order.setCustomerId(userID);

        orderRepository.sendOrder(order);



    }



}
