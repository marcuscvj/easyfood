package com.example.easyfood.db;

import com.example.easyfood.model.Eatery;

import java.util.ArrayList;

public interface IEateriesCallback {
    void send(ArrayList<Eatery> list);
}
