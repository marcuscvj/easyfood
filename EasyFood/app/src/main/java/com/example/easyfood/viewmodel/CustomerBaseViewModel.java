package com.example.easyfood.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.repository.OrderRepository;


public class CustomerBaseViewModel extends ViewModel {
    private OrderRepository orderRepository;
    private MutableLiveData<String> status;
    private String UID;

    /**
     * Initializes the ViewModel
     *
     * @param customerId : String - The id of the eatery
     */
    public void init(String customerId) {
        if (UID != null) {
            return;
        }

        UID = customerId;
        orderRepository = OrderRepository.getInstance();
        status = orderRepository.getOrderStatus(UID);

    }

    public LiveData<String> getOrderStatus() {
        return status;
    }
}
