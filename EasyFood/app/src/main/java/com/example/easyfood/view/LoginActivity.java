package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.User;
import com.example.easyfood.view.customer.EateryActivity;
import com.example.easyfood.viewmodel.LoginActivityViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity {
    private LoginActivityViewModel viewModel;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        viewModel.init();

        emailEditText = findViewById(R.id.loginEmail);
        passwordEditText = findViewById(R.id.loginPwd);
        loginButton = findViewById(R.id.btnLogin);

        setLoginButtonListener();
    }

    /**
     * Sets the On Click Listener for Login Button.
     */
    private void setLoginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailEditText.getText().toString();
                String userPwd = passwordEditText.getText().toString();

                if (userEmail.isEmpty()) {
                    emailEditText.setError("Provide your Email address first!");
                    emailEditText.requestFocus();
                } else if (userPwd.isEmpty()) {
                    passwordEditText.setError("Enter Password!");
                    passwordEditText.requestFocus();
                } else {
                    signInWithEmailAndPassword(userEmail, userPwd);
                }
            }
        });
    }

    /**
     * Signs in the user with Email and Password
     *
     * @param userEmail String - User email address
     * @param userPwd String - User password
     */
    private void signInWithEmailAndPassword(String userEmail, String userPwd) {
        viewModel.signInWithEmailAndPassword(userEmail, userPwd);
        viewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Intent intent = new Intent(getApplicationContext(), EateryActivity.class);
                startActivity(intent);
            }
        });
    }
}
