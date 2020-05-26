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
    private String eateryName;
    private int estimatedTimeInMinutes;

    public Order (String eateryId) {
        this.eateryId = eateryId;
        this.setPaid(false);
        this.setOrderStatus(Status.CREATED);
        this.setEstimatedTime(15);
        this.setPaymentMethod(PaymentMethod.PAY_AT_PICKUP);
    }

    public Order () {
        this.setPaid(false);
        this.setOrderStatus(Status.CREATED);
        this.setEstimatedTime(15);
        this.setPaymentMethod(PaymentMethod.PAY_AT_PICKUP);
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

    public int getEstimatedTime() {
        return estimatedTimeInMinutes;
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

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTimeInMinutes = estimatedTime;
    }

    public String getEateryName() {
        return eateryName;
    }

    public void setEateryName(String eateryName) {
        this.eateryName = eateryName;
    }

    //More getters and setters will be added here.


    /**
     * Payment Method Enum
     */
    public enum PaymentMethod {
        PAY_AT_PICKUP("PAY AT PICKUP");

        private String paymentMethod;

        PaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }
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