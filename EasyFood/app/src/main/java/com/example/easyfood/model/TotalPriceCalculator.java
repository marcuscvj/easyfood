package com.example.easyfood.model;

import java.util.ArrayList;

public class TotalPriceCalculator {
    private double total;

    public TotalPriceCalculator() {

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
