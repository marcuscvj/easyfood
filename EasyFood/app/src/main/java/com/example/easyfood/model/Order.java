package com.example.easyfood.model;

import java.util.ArrayList;

/**
 * Represents an Order
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

    /**
     * Creates an instance of Order
     *
     * @param eateryId : String - The eatery id
     */
    public Order (String eateryId) {
        this.eateryId = eateryId;
        this.setPaid(false);
        this.setOrderStatus(Status.CREATED);
        this.setEstimatedTime(15);
        this.setPaymentMethod(PaymentMethod.PAY_AT_PICKUP);
    }

    /**
     * Creates an instance of Order
     */
    public Order () {
        this.setPaid(false);
        this.setOrderStatus(Status.CREATED);
        this.setEstimatedTime(15);
        this.setPaymentMethod(PaymentMethod.PAY_AT_PICKUP);
    }

    /**
     * Returns the id of the eatery
     *
     * @return eateryId : String - The id of the eatery
     */
    public String getEateryId() {
        return eateryId;
    }

    /**
     * Sets the if of the order
     *
     * @param id : String - The id of the order
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the od of the order
     *
     * @return : String - The id of the order
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the estimated waiting time in minutes
     *
     * @return estimatedTimeInMinutes : int - The estimated waiting time in minutes
     */
    public int getEstimatedTime() {
        return estimatedTimeInMinutes;
    }

    /**
     * Returns the order number
     *
     * @return orderNumber : String - The order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns the message
     *
     * @return message : String - The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the payment method
     *
     * @return paymentMethod : PaymentMethod - The payment method
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Returns the payment status
     *
     * @return isPaid : boolean - Returns true if paid, else false
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Returns the order status
     *
     * @return orderStatus : Status - The status of the order
     */
    public Status getOrderStatus() {
        return orderStatus;
    }

    /**
     * Returns the total sum of the order
     *
     * @return sum : double - The total sum of the order
     */
    public double getSum() {
        return sum;
    }

    /**
     * Returns the customer id
     *
     * @return customerId : String - The customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the order number
     *
     * @param orderNumber : int - The order number
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Returns the products of the order
     *
     * @return products : ArrayList<Product> - The list of products
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Sets the products of the order
     *
     * @param products : ArrayList<Product> - The list of products
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * The message to the eatery
     *
     * @param message : String - The message to the eater
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets the payment method
     *
     * @param paymentMethod : PaymentMethod - The payment method
     */
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Sets the payment status
     *
     * @param paid : boolean - true/false
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Sets the order status of the order
     *
     * @param orderStatus : Status - The order status
     */
    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * The total sum of the order
     *
     * @param sum : double - The sum
     */
    public void setSum(double sum) {
        this.sum = sum;
    }

    /**
     * Sets the customer id of the order
     *
     * @param customerId : String - The id of the customer
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Sets the estimated waiting time of the order
     *
     * @param estimatedTime : int - The estimated time in minutes
     */
    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTimeInMinutes = estimatedTime;
    }

    /**
     * Returns the eatery name
     *
     * @return eateryName : String - The eatery name
     */
    public String getEateryName() {
        return eateryName;
    }

    /**
     * Sets the name of the eatery
     *
     * @param eateryName : String - The name of the eatery
     */
    public void setEateryName(String eateryName) {
        this.eateryName = eateryName;
    }


    /**
     * Payment Method Enum
     */
    public enum PaymentMethod {
        PAY_AT_PICKUP("Pay at pickup");

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
        CREATED("Created"),
        SENT("Sent to eatery"),
        CONFIRMED("Confirmed by eatery"),
        READY("Ready for pickup "),
        DELIVERED("Delivered to customer");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}