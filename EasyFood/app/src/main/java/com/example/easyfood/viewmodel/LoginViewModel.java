package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.User;
import com.example.easyfood.repository.UserRepository;

/**
 * Represents the ViewModel of LoginActivity
 */
public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;
    private LiveData<User> userLiveData;

    /**
     * Initializes the ViewModel
     */
    public void init(){
        userRepository = UserRepository.getInstance();
    }

    /**
     * Logs in the user with email and password
     *
     * @param email : String - The email address of the user
     * @param password : String - The password of the user
     */
    public void signInWithEmailAndPassword(String email, String password) {
        userLiveData = userRepository.signInWithEmailAndPassword(email, password);
    }

    /**
     * Returns a LiveData object of the user
     *
     * @return userLiveData : LiveData<User> - The user
     */
    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
