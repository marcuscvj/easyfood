package com.example.easyfood.model;

public class Product {
    private String title = null;
    private String description = null;
    private double price;
    //private String imagePathWay = null;

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }
}
