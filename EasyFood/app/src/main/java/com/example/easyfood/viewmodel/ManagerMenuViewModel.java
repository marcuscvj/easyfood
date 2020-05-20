package com.example.easyfood.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.easyfood.repository.ManagerMenuRepository;

public class ManagerMenuViewModel extends ViewModel {
    private ManagerMenuRepository managerMenuRepository;

    /**
     * Initializes the ViewModel
     */
    public void init(){
        managerMenuRepository = ManagerMenuRepository.getInstance();
    }

    /**
     * Creates a new product and adds it to the database
     *
     * @param eateryId : String - The id of the eatery
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     */
    public void createProduct(String eateryId, String name, String description, Double price) {
        managerMenuRepository.createProductAndAddToDatabase(eateryId, name, description, price);
    }

}
