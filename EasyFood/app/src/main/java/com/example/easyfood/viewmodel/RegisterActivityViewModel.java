package com.example.easyfood.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.easyfood.model.User;
import com.example.easyfood.repository.AuthRepository;

public class RegisterActivityViewModel extends ViewModel {
    private AuthRepository authRepository;

    public void init() {
        authRepository = AuthRepository.getInstance();
    }

    public void registerWithEmailAndPassword(String email, String password) {
        authRepository.registerWithEmailAndPassword(email, password);
    }
}
