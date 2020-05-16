package com.example.easyfood.model;

public class User {
    private String id;
    private String email;

    public User(String id, String email) {
        setId(id);
        setEmail(email);
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setEmail(String email) {
        this.email = email;
    }
}