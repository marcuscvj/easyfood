package com.example.easyfood.db;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.Product;

import java.util.ArrayList;

public interface IProductsCallback {
    void send(ArrayList<Product> list);
}
