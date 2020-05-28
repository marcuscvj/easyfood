package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.repository.OrderRepository;

import java.util.List;

public class CustomerBaseViewModel extends ViewModel {
    private MutableLiveData<List<Order>> orders;
    private OrderRepository orderRepository;
    private String UID;

    /**
     * Initializes the ViewModel
     *
     * @param customerId : String - The id of the eatery
     */
    public void init(String customerId) {
        if (orders != null) {
            UID = customerId;
            return;
        }

        orderRepository = OrderRepository.getInstance();
        orders = orderRepository.getAllOrdersForSpecificCustomer(customerId);
    }

    public MutableLiveData<String> getOrderStatus() {
        return orderRepository.getOrderStatus(this.UID);
    }
}
