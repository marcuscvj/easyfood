package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Order;
import com.example.easyfood.repository.OrderRepository;

public class ManagerOrderViewModel extends ViewModel {
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

    /**
     * Updates the order status
     *
     * @param orderId - String - The id of the order
     * @param status - boolean - The status of the order
     */
    public void updateOrderStatus(String orderId, Order.Status status) {
        if (order.getValue().getOrderStatus() != status) {
            orderRepository.setOrderStatus(orderId, status);
        }
    }

    /**
     * Updates the payment status
     *
     * @param orderId - String - The id of the order
     * @param status - boolean - The status of the payment
     */
    public void updatePaymentStatus(String orderId, boolean status) {
        if (order.getValue().isPaid() != status) {
            orderRepository.setPaymentStatus(orderId, status);
        }
    }

}
