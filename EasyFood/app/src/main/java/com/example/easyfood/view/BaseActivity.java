package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Base Activity
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Goes to Activity
     *
     * @param intent Intent - The intent
     */
    protected void goToActivity(Intent intent) {
        startActivity(intent);
    }
}
