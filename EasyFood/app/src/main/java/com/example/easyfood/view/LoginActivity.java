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
import com.example.easyfood.model.Eatery;
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
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        viewModel.init();

        firebaseAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.loginEmail);
        passwordEditText = findViewById(R.id.loginPwd);
        loginButton = findViewById(R.id.btnLogin);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                Toast.makeText(LoginActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();

                if (user != null) {
                    Intent I = new Intent(getApplicationContext(), EateryActivity.class);
                    startActivity(I);
                } else {
                    Toast.makeText(getApplicationContext(), "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

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
                } else if (userEmail.isEmpty() && userPwd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPwd.isEmpty())) {
                        v(userEmail, userPwd);
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    private void v(String userEmail, String userPwd) {
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
