package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.repository.OrderRepository;

import java.util.List;

/**
 * Represents the ViewModel of CustomerOrdersActivity
 */
public class CustomerOrdersViewModel extends ViewModel {
    private MutableLiveData<List<Order>> orders;
    private OrderRepository orderRepository;

    /**
     * Initializes the ViewModel
     *
     * @param customerId : String - The id of the customer
     */
    public void init(String customerId) {
        if (orders != null) {
            return;
        }
        orderRepository = OrderRepository.getInstance();
        orders = orderRepository.getAllOrdersForSpecificCustomer(customerId);
    }

    /**
     * Returns a list of all orders
     *
     * @return orders - LiveData<List<Order>> - The list of orders
     */
    public LiveData<List<Order>> getOrders() {
        return orders;
    }
}
