package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.repository.EateryRepository;

public class MainViewModel extends ViewModel {
    private EateryRepository eateryRepository;
    private MutableLiveData<Eatery> eatery;

    public void init() {
        eateryRepository = EateryRepository.getInstance();
    }

    public LiveData<Eatery> getEateryId(String managerId) {
        eatery = eateryRepository.getSpecificEatery(managerId);
        return eatery;
    }
}
