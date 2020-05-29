package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.User;
import com.example.easyfood.view.customer.EateryActivity;
import com.example.easyfood.viewmodel.RegisterViewModel;

/**
 * Register Activity
 */
public class RegisterActivity extends BaseActivity {
    RegisterViewModel viewModel;

    EditText emailEditText;
    EditText passwordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        viewModel.init();

        emailEditText = findViewById(R.id.email_editText);
        passwordEditText = findViewById(R.id.password_editText);
        registerButton = findViewById(R.id.register_button);

        setRegisterButtonListener();
    }

    /**
     * Sets the On Click Listener for Register Button
     */
    private void setRegisterButtonListener() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    emailEditText.setError("Email is Required");
                    emailEditText.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password is Required");
                    passwordEditText.requestFocus();
                    return;
                }

                if(password.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters long");
                    passwordEditText.requestFocus();
                    return;
                }

                registerWithEmailAndPassword(email, password);
            }
        });
    }

    /**
     * Registers the user with Email and Password
     *
     * @param email String - User email address
     * @param password String - User password
     */
    private void registerWithEmailAndPassword(String email, String password) {
        viewModel.registerWithEmailAndPassword(email, password);
        viewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user.getId().equals("0")) {
                    emailEditText.setError("Wrong Credentials");
                    passwordEditText.setError("Wrong Credentials");
                    return;
                }

                goToActivity(new Intent(getApplicationContext(), EateryActivity.class));
                Toast.makeText(getApplicationContext(), R.string.account_register_successful, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
