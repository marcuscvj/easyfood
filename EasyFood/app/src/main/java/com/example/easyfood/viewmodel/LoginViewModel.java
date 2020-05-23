package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.User;
import com.example.easyfood.repository.UserRepository;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;
    private LiveData<User> userLiveData;

    public void init(){
        userRepository = UserRepository.getInstance();
    }

    public void signInWithEmailAndPassword(String email, String password) {
        userLiveData = userRepository.signInWithEmailAndPassword(email, password);
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
