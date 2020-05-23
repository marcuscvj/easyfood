package com.example.easyfood.repository;

import com.example.easyfood.model.Order;

import java.util.ArrayList;

public interface IOrdersCallback {
    void send(ArrayList<Order> list);
}
