package com.example.easyfood.model;

// TODO SNYGGA TILL MED JAVADOCS

import java.io.Serializable;

/**
 * Represents a Product
 */
public class Product implements Serializable { // TODO Remove Serializable, Was only here for testing
    private String name;
    private String description;
    private Double price;
    private String id;

    /**
     * Creates an instance of a product
     *
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     * @param id : String - The id of the product
     */
    public Product(String name, String description, double price, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

    /**
     * Creates an instance of a product
     */
    public Product() {}

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public void setId(String id) {
        this.id = id;
    }
}
