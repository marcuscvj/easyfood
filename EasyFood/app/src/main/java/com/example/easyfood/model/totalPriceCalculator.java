package com.example.easyfood.model;

import java.util.ArrayList;

public class totalPriceCalculator {
    private double total;

    public totalPriceCalculator () {

    }

    public double getTotalPriceOfProducts(ArrayList<Product> products) {
        total = 0.0;
        for (Product item : products)
        {
            total += item.getPrice();
        }
        return total;
    }
}
