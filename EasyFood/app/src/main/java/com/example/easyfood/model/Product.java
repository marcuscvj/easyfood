package com.example.easyfood.model;

/**
 * Represents a Product
 */
public class Product {
    private String name;
    private String description;
    private Double price;
    private String id;
    private String category;

    /**
     * Creates an instance of a product
     *
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     * @param id : String - The id of the product
     */
    public Product(String name, String description, double price, String id, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.category = category;
    }

    /**
     * Creates an instance of a product
     */
    public Product() {}

    /**
     * Sets the id of the product
     *
     * @param id : String - The id of the product
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the id of the product
     *
     * @return id : String - The id of the product
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the name of the product
     *
     * @return name : String - The name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of the product
     *
     * @return description : String - The description of the product
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the price of the product
     *
     * @return price : Double - The price of the product
     */
    public double getPrice() {
        return this.price;
    }


    /**
     * Returns the category of the product
     *
     * @return category : String - The category of the product
     */
    public String getCategory() {
        return this.category;
    }


}
