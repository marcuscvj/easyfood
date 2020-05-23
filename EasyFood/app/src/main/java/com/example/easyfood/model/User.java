package com.example.easyfood.model;

// TODO What attributes does the user need ?

/**
 * Represents a user
 */
public class User {
    private String id;
    private String email;
    private Role role;

    /**
     * Creates an instance of a user
     * @param id : String - The id of the user
     * @param email : String - The email address of the user
     */
    public User(String id, String email) {
        setId(id);
        setEmail(email);
        this.role = Role.CUSTOMER;
    }

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
     * Inner enum Role class
     */
    public enum Role {
        MANAGER, CUSTOMER
    }
}
