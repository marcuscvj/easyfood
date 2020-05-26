package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.view.MainActivity;
import com.example.easyfood.view.customer.EateryActivity;
import com.example.easyfood.viewmodel.ManagerSettingsViewModel;


public class ManagerSettingsActivity extends ManagerBaseActivity {
    private String UID;

    private ManagerSettingsViewModel viewModel;

    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private Button logoutButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_settings);

        setEateryId();

        UID = firebaseAuth.getCurrentUser().getUid();

        viewModel = new ViewModelProvider(this).get(ManagerSettingsViewModel.class);
        viewModel.init(UID);

        emailEditText = findViewById(R.id.email_editText);
        phoneNumberEditText = findViewById(R.id.phoneNumber_editText);

        /*viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                emailEditText.setText(user.getEmail());
                phoneNumberEditText.setText(user.getPhoneNumber());
            }
        });*/

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
        viewModel.updateUser(UID, phoneNumberEditText.getText().toString());
        startActivity(new Intent(this, EateryActivity.class));
    }

    protected void logout() {
        firebaseAuth.signOut();
        startActivity(new Intent(this, MainActivity.class));
    }
}