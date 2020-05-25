package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.model.User;
import com.example.easyfood.view.SettingsActivity;
import com.example.easyfood.viewmodel.CustomerSettingsViewModel;

import java.util.List;
import java.util.Objects;


public class CustomerSettingsActivity extends SettingsActivity {
    private String UID;

    private CustomerSettingsViewModel viewModel;

    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private Button logoutButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_settings);

        UID = firebaseAuth.getCurrentUser().getUid();

        viewModel = new ViewModelProvider(this).get(CustomerSettingsViewModel.class);
        viewModel.init(UID);

        emailEditText = findViewById(R.id.email_editText);
        phoneNumberEditText = findViewById(R.id.phoneNumber_editText);

        // setInitialData(emailEditText, phoneNumberEditText);

//        viewModel.getUser().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                System.out.println("USER: " + user);
//                // emailEditText.setText(user.getEmail());
//                // honeNumberEditText.setText(user.getPhoneNumber());
//            }
//        });

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

    private void setInitialData(final EditText emailEditText, final EditText phoneNumberEditText) {

    }

    private void save() {
        viewModel.updateUser(UID, phoneNumberEditText.getText().toString());
        startActivity(new Intent(this, EateryActivity.class));
    }
}