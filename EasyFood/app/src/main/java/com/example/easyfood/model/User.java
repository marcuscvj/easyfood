package com.example.easyfood.model;

/**
 * Represents a user
 */
public class User {
    private String id;
    private String email;
    private Role role;
    private String phoneNum;

    /**
     * Creates an instance of a user
     *
     * @param id : String - The id of the user
     * @param email : String - The email address of the user
     */
    public User(String id, String email) {
        setId(id);
        setEmail(email);
        this.role = Role.CUSTOMER;
        this.phoneNum = "";
    }

    /**
     * Creates an instance of a user
     */
    public User() {}

    /**
     * Sets the id of the user
     *
     * @param id : String - The id of the user
     */
    private void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the email address of the user
     *
     * @param email : String - The email address of the user
     */
    private void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the id of the user
     *
     * @return id : String - The id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the email address of the user
     *
     * @return email : String - The email address of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the role of the User
     *
     * @return role: Role (Enum) - The role of the User
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Sets a new role to the User
     *
     * @param role: Role (Enum) - The role of the User
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Returns the phone number of the User
     *
     * @return phoneNum: String - The phone number of the User
     */
    public String getPhoneNumber() {
        return this.phoneNum;
    }

    /**
     * Sets a new phone number to the User
     *
     * @param pn: String - The phone number of the User
     */
    public void setPhoneNumber(String pn) {
        this.phoneNum = pn;
    }

    /**
     * Inner enum Role class
     */
    public enum Role {
        MANAGER, CUSTOMER
    }
}
