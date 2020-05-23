package com.example.easyfood.model;

/**
 * Represents an Eatery
 */
public class Eatery {
    private String name;
    private String id;
    private int phoneNumber;
    private String street;
    private int streetNumber;
    private int postalCode;
    private String city;
    private String managerId;
    private String openingHours;

    /**
     * Creates an instance of Eatery
     *
     * @param name: String - The name of the Eatery
     */
    public Eatery (String name, String id) {
        setName(name);
        this.id = id;
    }

    /**
     * Creates an instance of Eatery
     */
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

    /**
     * Returns the id of the Eatery
     *
     * @param id: String - The id of the Eatery
     */
    public void setId (String id) {
        this.id = id;
    }

    /**
     * Returns the manager id of the Eatery
     *
     * @return managerId : String - The manager id
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * Sets the manager id
     *
     * @param managerId : String - The manager id
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
}
