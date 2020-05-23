package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.User;
import com.example.easyfood.repository.UserRepository;

public class MainViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<User> user;

    public void init() {
        userRepository = UserRepository.getInstance();
    }

    public LiveData<User> getUser(String userId) {
        user = userRepository.getCurrentUser(userId);
        return user;
    }
}
