package com.example.easyfood.model;

import java.util.ArrayList;


/**
 * Holds the information about an order that has been placed.
 */
public class Order {

    private String id;
    private int orderNumber;
    private ArrayList<Product> products;
    private String message;
    private OrderPaymentMethodEnums paymentMethod;
    private boolean isPaid;
    private OrderStatusEnums orderStatus;
    private double sum;
    private String customerId;
    private String restaurantId;

    public Order (String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getMessage() {
        return message;
    }

    public OrderPaymentMethodEnums getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public OrderStatusEnums getOrderStatus() {
        return orderStatus;
    }

    public double getSum() {
        return sum;
    }

    public String getCustomerId() {
        return customerId;
    }

    //More getters and setters will be added here.
}
