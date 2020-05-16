package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.User;
import com.example.easyfood.repository.AuthRepository;

public class LoginActivityViewModel extends ViewModel {
    private AuthRepository authRepository;
    private LiveData<User> userLiveData;

    public void init(){
        authRepository = AuthRepository.getInstance();
    }

    public void signInWithEmailAndPassword(String email, String password) {
        userLiveData = authRepository.signInWithEmailAndPassword(email, password);
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
