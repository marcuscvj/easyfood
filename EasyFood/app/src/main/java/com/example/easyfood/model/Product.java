package com.example.easyfood.model;

public class Product {
    private String name;
    private String description;
    private double price;
    private String id;
    //private String imagePathWay = null;

    public Product(String name, String description, double price, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
    }

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
}
