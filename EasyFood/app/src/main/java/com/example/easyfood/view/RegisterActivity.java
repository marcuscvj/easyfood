package com.example.easyfood.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.viewmodel.RegisterActivityViewModel;

public class RegisterActivity extends BaseActivity {
    RegisterActivityViewModel viewModel;

    EditText emailEditText;
    EditText passwordEditText;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterActivityViewModel.class);
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
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    passwordEditText.setError("Password is Required");
                    return;
                }

                if(password.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters long");
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
    }
}
