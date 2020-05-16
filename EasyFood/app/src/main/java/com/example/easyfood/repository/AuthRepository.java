package com.example.easyfood.repository;

public class AuthRepository {
    private static AuthRepository instance;

    public static AuthRepository getInstance() {
        if (instance == null){
            instance = new AuthRepository();
        }
        return instance;
    }

}
