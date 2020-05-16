package com.example.easyfood.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.easyfood.repository.AuthRepository;

public class LoginActivityViewModel extends ViewModel {
    private AuthRepository authRepository;

    public void init(){
        authRepository = AuthRepository.getInstance();
    }
}
