package com.example.easyfood.model;

// TODO SNYGGA TILL MED JAVADOCS

import java.io.Serializable;

public class Product implements Serializable { // TODO Remove Serializable, Was only here for testing
    private String name;
    private String description;
    private Double price;
    private String id;
    //private String imagePathWay = null;

    public Product(String name, String description, double price, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

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
