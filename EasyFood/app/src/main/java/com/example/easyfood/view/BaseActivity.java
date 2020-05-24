package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easyfood.R;
import com.example.easyfood.view.customer.BasketActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Base Activity
 *
 * TEMPORARY:
 * The Base Activity could be a Master Class for all/some activities that share functionality
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
