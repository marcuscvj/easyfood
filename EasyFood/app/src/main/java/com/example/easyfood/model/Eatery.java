package com.example.easyfood.model;

/**
 * Represents an Eatery
 */
public class Eatery {
    private String name;
    private String id;

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
    private void setName(String name) {
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
}
