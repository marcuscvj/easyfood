package com.example.easyfood.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.Eatery;

import java.util.ArrayList;
import java.util.List;

public class EateryRepository {
    private static EateryRepository instance;
    private ArrayList<Eatery> eateryList = new ArrayList<>();

    public static EateryRepository getInstance() {
        if (instance == null){
            instance = new EateryRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Eatery>> getEateries() {
        eateryList.add(new Eatery("Erkut Pizzera & Kebabcenter"));
        eateryList.add(new Eatery("Bellas Pizzeria"));
        eateryList.add(new Eatery("Majas Pizzera"));

        MutableLiveData<List<Eatery>> liveData = new MutableLiveData<>();
        liveData.setValue(eateryList);
        return liveData;
    }
}
