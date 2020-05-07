package com.example.easyfood.model;

import java.util.List;

/**
 * <h1>Order Class</h1>
 * Holds the information about an order that has been placed.
 */
public class Order {
    private Object customer;
    private int orderId;
    private int orderNr;
    private List<Object> products;
    private Object payment;

    public Order(Object customer, int orderId, int orderNr, Object payment, List<Object> products) {
        this.customer = customer;
        this.orderId = orderId;
        this.orderNr = orderNr;
        this.products = products;
        this.payment = payment;
    }

    /**
     * Returns a string of all the order details.
     * @return String
     */
    public String getOrder() {
        return "Customer " + this.customer.toString() +
                " Order ID: " + this.orderId +
                " Order Number: " + this.orderNr +
                " Payment: " + this.payment.toString() +
                " Products:" + this.products.toString();
    }
    
}
