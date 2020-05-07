package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.model.Eatery;

import java.util.ArrayList;
import java.util.List;

/**
 * EateryRepository - Singleton
 */
public class EateryRepository {
    private static EateryRepository instance;
    private Firebase fb = new Firebase();
    private ArrayList<Eatery> eateryList = new ArrayList<>();

    /**
     * Returns an instance of the EateryRepository
     *
     * @return EateryRepository: instance - The instance of the EateryRepository.
     */
    public static EateryRepository getInstance() {
        if (instance == null){
            instance = new EateryRepository();
        }
        return instance;
    }

    /**
     * Returns the eateries.
     *
     * @return MutableLiveData<List<Eatery>>: eateries - The eateries.
     */
    public MutableLiveData<List<Eatery>> getEateries() {
        // TODO Get eateries from database.

        // Remove this later and put on more suitable place
        // Adding to db
        // Tests

        Eatery eatery1 = new Eatery("Erkut Pizzera & Kebabcenter", "test1");
        Eatery eatery2 = new Eatery("Bellas Pizzeria", "test2");
        Eatery eatery3 = new Eatery("Majas Pizzera", "test3");

        fb.addEatery(eatery1);
        fb.addEatery(eatery2);
        fb.addEatery(eatery3);

        fb.getEatery(eatery2);


        eateryList.add(eatery1);
        eateryList.add(eatery2);
        eateryList.add(eatery3);

        MutableLiveData<List<Eatery>> eateries = new MutableLiveData<>();
        eateries.setValue(eateryList);
        return eateries;
    }
}
