package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.User;
import com.example.easyfood.repository.UserRepository;


public class ManagerSettingsViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<User> user;

    /**
     * Initializes the ViewModel
     */
    public void init(String userId){
        if(user != null) {
            return;
        }
        userRepository = userRepository.getInstance();
        user = userRepository.getCurrentUser(userId);
    }

    /**
     * Returns the products.
     *
     * @return LiveData: products - The products.
     */
    public LiveData<User> getUser() {
        return user;
    }

    /**
     *
     * Updates a user
     *
     * @param phoneNumber : String - The phone number of the user
     */
    public void updateUser(String UID, String phoneNumber) {
        userRepository.updateUser(UID, phoneNumber);
    }
}
