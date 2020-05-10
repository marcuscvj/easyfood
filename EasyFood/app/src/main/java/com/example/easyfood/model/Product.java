package com.example.easyfood.model;

public class Product {

    private String title = null;
    private String description = null;
    private int price;
    //private String imagePathWay = null;

    public Product(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

}
