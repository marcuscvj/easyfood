package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Eatery;

import java.util.ArrayList;
import java.util.List;

/**
 * EateryRepository - Singleton
 */
public class EateryRepository {
    private static EateryRepository instance;
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

        eateryList.add(new Eatery("Erkut Pizzera & Kebabcenter"));
        eateryList.add(new Eatery("Bellas Pizzeria"));
        eateryList.add(new Eatery("Majas Pizzera"));

        MutableLiveData<List<Eatery>> eateries = new MutableLiveData<>();
        eateries.setValue(eateryList);
        return eateries;
    }
}
