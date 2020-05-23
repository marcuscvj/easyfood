package com.example.easyfood.model;

import java.util.ArrayList;


/**
 * Holds the information about an order that has been placed.
 */
public class Order {

    private  String OrderID;
    private int OrderNumber;
    private ArrayList<Product> products;
    private String message;
    private OrderPaymentMethodEnums paymentMethod;
    private boolean isPaid;
    private OrderStatusEnums orderStatus;
    private double sum;
    private String customerID;
    private String restaurantID;

    public Order (String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    //More getters and setters will be added here.
}
