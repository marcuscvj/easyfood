package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.repository.OrderRepository;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<Order> order;
    private OrderRepository orderRepository;

    public void init(String orderId) {
        if (order != null) {
            return;
        }

        orderRepository = OrderRepository.getInstance();
        order = orderRepository.getOrder(orderId);
    }
    /**
     * Returns an order
     *
     * @return orders - LiveData<List<Order>> - The orders
     */
    public LiveData<Order> getOrder() {
        return order;
    }

    public void updateOrderStatus(String orderId, Order.Status status) {
        orderRepository.setOrderStatus(orderId, status);
    }

}
