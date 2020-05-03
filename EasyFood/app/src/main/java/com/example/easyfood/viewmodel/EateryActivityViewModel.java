package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.repository.EateryRepository;

import java.util.List;

public class EateryActivityViewModel extends ViewModel {
    private MutableLiveData<List<Eatery>> eateries;
    private EateryRepository eateryRepository;

    public void init(){
        if(eateries != null) {
            return;
        }
        eateryRepository = EateryRepository.getInstance();
        eateries = eateryRepository.getEateries();
    }

    public LiveData<List<Eatery>> getEateries() {
        return eateries;
    }
}
