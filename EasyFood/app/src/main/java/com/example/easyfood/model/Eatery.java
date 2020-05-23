package com.example.easyfood.model;

import java.util.ArrayList;

/**
 * Represents an Eatery
 */
public class Eatery {
    private String name;
    private String id;
    private String managerId;
    private ArrayList<Product> products = new ArrayList<>();

    /**
     * Creates an instance of Eatery
     *
     * @param name: String - The name of the Eatery
     */
    public Eatery (String name, String id) {
        setName(name);

        // generate id here
        // Parameter id above is only now for testing purposes
        this.id = id;
    }

    public Eatery () {}

    /**
     * Returns the name of the Eatery
     *
     * @return name: String - The name of the Eatery
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the Eatery
     *
     * @param name: String - The name of the Eatery
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the id of the Eatery
     *
     * @return id: String - The id of the Eatery
     */
    public String getId() {
        return this.id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
