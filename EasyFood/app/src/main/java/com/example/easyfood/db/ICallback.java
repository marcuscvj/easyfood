package com.example.easyfood.db;

import com.example.easyfood.model.Eatery;

import java.util.ArrayList;

public interface ICallback {
    void eateriesCallback(ArrayList<Eatery> eateryList);
}
