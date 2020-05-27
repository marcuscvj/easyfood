package com.example.easyfood.view;

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
import com.example.easyfood.viewmodel.LoginViewModel;

public class LoginActivity extends BaseActivity {
    private LoginViewModel viewModel;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        viewModel.init();

        emailEditText = findViewById(R.id.email_editText);
        passwordEditText = findViewById(R.id.password_editText);
        loginButton = findViewById(R.id.login_button);

        setLoginButtonListener();
    }

    /**
     * Sets the On Click Listener for Login Button.
     */
    private void setLoginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEditText.setError("Provide your Email address first!");
                    emailEditText.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Enter Password!");
                    passwordEditText.requestFocus();
                    return;
                }

                signInWithEmailAndPassword(email, password);
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
                if (user.getId().equals("0")) {
                    emailEditText.setError("Wrong Credentials");
                    passwordEditText.setError("Wrong Credentials");
                    return;
                }

                goToActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), R.string.login_successful, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
