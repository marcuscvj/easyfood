package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.User;
import com.example.easyfood.repository.EateryRepository;
import com.example.easyfood.repository.UserRepository;

public class MainViewModel extends ViewModel {
    private UserRepository userRepository;
    private EateryRepository eateryRepository;
    private MutableLiveData<User> user;
    private MutableLiveData<Eatery> eatery;

    public void init() {
        userRepository = UserRepository.getInstance();
        eateryRepository = EateryRepository.getInstance();
    }

    public LiveData<User> getUser(String userId) {
        user = userRepository.getCurrentUser(userId);
        return user;
    }

    public LiveData<Eatery> getEatery(String managerId) {
        eatery = eateryRepository.getSpecificEatery(managerId);
        return eatery;
    }
}
