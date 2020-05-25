package com.example.easyfood.view;

import android.content.Intent;

public class SettingsActivity extends BaseActivity {
    private void logout() {
        firebaseAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}