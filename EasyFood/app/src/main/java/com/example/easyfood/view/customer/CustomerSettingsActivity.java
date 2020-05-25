package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.easyfood.R;
import com.example.easyfood.view.SettingsActivity;


public class CustomerSettingsActivity extends SettingsActivity {
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private Button logoutButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        saveButton = findViewById(R.id.save_button);
        logoutButton = findViewById(R.id.logout_button);

        saveButtonListener();
        logoutButtonListener();
    }

    private void logoutButtonListener() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void saveButtonListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    private void save() {
        // Check here if customer or
        startActivity(new Intent(this, EateryActivity.class));
    }
}