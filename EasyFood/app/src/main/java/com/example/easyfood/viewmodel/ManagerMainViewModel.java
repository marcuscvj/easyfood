package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.repository.EateryRepository;

public class ManagerMainViewModel extends ViewModel {
    private EateryRepository eateryRepository;
    private MutableLiveData<Eatery> eatery;

    public void init(String managerId) {
        eateryRepository = EateryRepository.getInstance();
        eatery = eateryRepository.getSpecificEatery(managerId);
    }

    public LiveData<Eatery> getUser() {
        return eatery;
    }
}
