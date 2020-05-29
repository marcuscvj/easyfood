package com.example.easyfood.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.User;
import com.example.easyfood.repository.EateryRepository;
import com.example.easyfood.repository.UserRepository;

/**
 * Represents the ViewModel of MainActivity
 */
public class MainViewModel extends ViewModel {
    private UserRepository userRepository;
    private EateryRepository eateryRepository;
    private MutableLiveData<User> user;
    private MutableLiveData<Eatery> eatery;

    /**
     * Initializes the ViewModel
     */
    public void init() {
        userRepository = UserRepository.getInstance();
        eateryRepository = EateryRepository.getInstance();
    }

    /**
     * Returns a LiveData object of the user
     *
     * @param userId : String - The id of the user
     * @return user : LiveData<User> - The user
     */
    public LiveData<User> getUser(String userId) {
        user = userRepository.getCurrentUser(userId);
        return user;
    }

    /**
     * Returns a LiveData object of the eatery
     *
     * @param managerId : String - The id of the manager of the eatert
     * @return eatery : LiveData<Eatery> - The eatery
     */
    public LiveData<Eatery> getEatery(String managerId) {
        eatery = eateryRepository.getSpecificEatery(managerId);
        return eatery;
    }
}
