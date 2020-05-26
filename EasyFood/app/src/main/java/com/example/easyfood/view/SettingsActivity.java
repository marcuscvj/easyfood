package com.example.easyfood.view;

import android.content.Intent;

import com.example.easyfood.view.customer.EateryActivity;

public class SettingsActivity extends BaseActivity {
    protected void logout() {
        firebaseAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}