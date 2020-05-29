package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.User;
import com.example.easyfood.view.MainActivity;
import com.example.easyfood.viewmodel.CustomerSettingsViewModel;


public class CustomerSettingsActivity extends CustomerBaseActivity {
    private String userId;

    private CustomerSettingsViewModel viewModel;

    private EditText phoneNumberEditText;
    private Button logoutButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        userId = firebaseAuth.getCurrentUser().getUid();

        viewModel = new ViewModelProvider(this).get(CustomerSettingsViewModel.class);
        viewModel.init(userId);

        phoneNumberEditText = findViewById(R.id.phoneNumber_editText);

        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                phoneNumberEditText.setText(user.getPhoneNumber());
            }
        });

        saveButton = findViewById(R.id.save_button);
        logoutButton = findViewById(R.id.logout_button);

        saveButtonListener();
        logoutButtonListener();
    }

    /**
     * Sets the logout button listener
     */
    private void logoutButtonListener() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    /**
     * Sets the save button listener
     */
    private void saveButtonListener() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    /**
     * Saves the new settings
     */
    private void save() {
        viewModel.updateUser(userId, phoneNumberEditText.getText().toString());
        startActivity(new Intent(this, EateryActivity.class));
        Toast.makeText(getApplicationContext(), R.string.info_updated, Toast.LENGTH_SHORT).show();
    }

    protected void logout() {
        firebaseAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}