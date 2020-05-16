package com.example.easyfood.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.easyfood.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepository {
    private String TAG = "AuthRepository";
    private static AuthRepository instance;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static AuthRepository getInstance() {
        if (instance == null){
            instance = new AuthRepository();
        }
        return instance;
    }

    public MutableLiveData<User> signInWithEmailAndPassword(String email, String password) {
        final MutableLiveData<User> userLiveData = new MutableLiveData<>();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if(firebaseUser != null) {
                        String id = firebaseUser.getUid();
                        String email = firebaseUser.getEmail();
                        User user = new User(id, email);
                        userLiveData.setValue(user);
                    }
                } else {
                    Log.d(TAG, task.getException().getMessage());
                }
            }
        });
        return userLiveData;
    }


    public void registerWithEmailAndPassword(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password);
        // TODO Check that user not exists etc.
        // TODO Send back notice that user is created.
    }

}
