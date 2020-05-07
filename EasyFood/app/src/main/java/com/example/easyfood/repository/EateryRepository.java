package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.db.Firebase;
import com.example.easyfood.db.IEateriesCallback;
import com.example.easyfood.model.Eatery;

import java.util.ArrayList;
import java.util.List;

/**
 * EateryRepository - Singleton
 */
public class EateryRepository {
    private static EateryRepository instance;
    private static Firebase fb;
    private ArrayList<Eatery> eateryList = new ArrayList<>();
    private MutableLiveData<List<Eatery>> eateries = new MutableLiveData<>();

    /**
     * Returns an instance of the EateryRepository
     *
     * @return EateryRepository: instance - The instance of the EateryRepository.
     */
    public static EateryRepository getInstance() {
        if (instance == null){
            instance = new EateryRepository();
            fb = new Firebase();
        }
        return instance;
    }

    /**
     * Returns the eateries.
     *
     * @return MutableLiveData<List<Eatery>>: eateries - The eateries.
     */
    public MutableLiveData<List<Eatery>> getEateries() {
        fb.getAllEateries(new IEateriesCallback() {
            @Override
            public void send(ArrayList<Eatery> list) {
                eateryList.addAll(list);
            }
        });

        eateries.setValue(eateryList);
        return eateries;
    }
}
