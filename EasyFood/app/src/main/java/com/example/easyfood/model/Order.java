package com.example.easyfood.model;

import java.util.ArrayList;

// TODO SNYGGA TILL DENNA RÖRA
/**
 * Holds the information about an order that has been placed.
 */
public class Order {
    private String id;
    private int orderNumber;
    private ArrayList<Product> products;
    private String message;
    private PaymentMethod paymentMethod;
    private boolean isPaid;
    private Status orderStatus;
    private double sum;
    private String customerId;
    private String eateryId;
    private String estimatedTime;

    public Order (String eateryId) {
        this.eateryId = eateryId;
        this.setPaid(false);
        this.setOrderStatus(Status.CREATED);
        this.setEstimatedTime("15 min");
        this.setPaymentMethod(PaymentMethod.CASH);
    }

    public Order () {
        this.setPaid(false);
        this.setOrderStatus(Status.CREATED);
        this.setEstimatedTime("15 min");
        this.setPaymentMethod(PaymentMethod.CASH);
    }

    public String getEateryId() {
        return eateryId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getMessage() {
        return message;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Status getOrderStatus() {
        return orderStatus;
    }

    public double getSum() {
        return sum;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    //More getters and setters will be added here.


    /**
     * Payment Method Enum
     */
    public enum PaymentMethod {
        CASH,
        CARD
        //Maybe they should be written in another way?
    }

    /**
     * Order Status Enum
     */
    public enum Status {
        CREATED("CREATED"),
        SENT("SENT"),
        CONFIRMED("CONFIRMED"),
        READY("READY"),
        DELIVERED("DELIVERED");
        //Enums should be added here. These two are mostly for testing.

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}