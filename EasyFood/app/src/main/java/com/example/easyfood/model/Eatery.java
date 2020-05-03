package com.example.easyfood.model;

public class Eatery {
    private String name;

    public Eatery (String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
