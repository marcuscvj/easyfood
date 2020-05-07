package com.example.easyfood.db;

import com.example.easyfood.model.Eatery;

import java.util.ArrayList;

public interface IDatabase {
    void addEatery(Eatery eatery);
    Eatery getEatery(Eatery eatery);
    void removeEatery(Eatery eatery);
    void updateEatery(Eatery eatery);
    ArrayList<Eatery> getAllEateries(ICallback callback);

//    void addMenuItem(Eatery eatery);
//    void getMenuItem();
//    void removeMenuItem();
//    void updateMenuItem();
//    void getAllMenuItems(Eatery eatery);
}
