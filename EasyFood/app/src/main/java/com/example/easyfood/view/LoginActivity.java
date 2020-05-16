package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.viewmodel.LoginActivityViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends BaseActivity {
    private LoginActivityViewModel viewModel;

    private EditText loginEmailId;
    private EditText loginPwd;
    private Button btnLogin;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        viewModel.init();

        firebaseAuth = FirebaseAuth.getInstance();
        loginEmailId = findViewById(R.id.loginEmail);
        loginPwd = findViewById(R.id.loginPwd);
        btnLogin = findViewById(R.id.btnLogin);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    Intent I = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(I);
                } else {
                    Toast.makeText(getApplicationContext(), "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = loginEmailId.getText().toString();
                String userPwd = loginPwd.getText().toString();

                if (userEmail.isEmpty()) {
                    loginEmailId.setError("Provide your Email address first!");
                    loginEmailId.requestFocus();
                } else if (userPwd.isEmpty()) {
                    loginPwd.setError("Enter Password!");
                    loginPwd.requestFocus();
                } else if (userEmail.isEmpty() && userPwd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPwd.isEmpty())) {
                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Unsuccessfull login attempt!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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
}
