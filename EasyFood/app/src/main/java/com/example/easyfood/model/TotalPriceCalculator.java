package com.example.easyfood.model;

import java.util.List;

/**
 * Represents a TotalPriceCalculator
 */
public class TotalPriceCalculator {

    /**
     * Returns the total price of products
     *
     * @return total : double
     */
    public double getTotalPriceOfProducts(List<Product> products) {
        double total = 0.0;
        for (Product item : products)
        {
            total += item.getPrice();
        }
        return total;
    }
}
