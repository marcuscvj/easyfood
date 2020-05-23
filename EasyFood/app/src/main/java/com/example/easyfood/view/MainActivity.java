package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.User;
import com.example.easyfood.view.customer.EateryActivity;
import com.example.easyfood.view.manager.ManagerMainActivity;
import com.example.easyfood.viewmodel.MainViewModel;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Main Activity
 *
 * TEMPORARY:
 * Contains a Login and a Register Button.
 */
public class MainActivity extends BaseActivity implements FirebaseAuth.AuthStateListener {
    MainViewModel viewModel;

    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        showMainActivity(false);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init();

        setLoginButtonListener();
        setRegisterButtonListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() != null) {
            viewModel.getUser(firebaseAuth.getCurrentUser().getUid()).observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user.getRole().equals(User.Role.MANAGER)) {
                        goToActivity(new Intent(getApplicationContext(), ManagerMainActivity.class));
                    } else {
                        goToActivity(new Intent(getApplicationContext(), EateryActivity.class));
                    }
                }
            });

        } else {
            showMainActivity(true);
        }
    }

    /**
     * Sets the OnClickListener for the Login Button.
     */
    private void setLoginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    /**
     * Sets the OnClickListener for the Register Button.
     */
    private void setRegisterButtonListener() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    private void showMainActivity(boolean show) {
        if (show) {
            loginButton.setVisibility(View.VISIBLE);
            registerButton.setVisibility(View.VISIBLE);
        } else {
            loginButton.setVisibility(View.INVISIBLE);
            registerButton.setVisibility(View.INVISIBLE);
        }
    }

}
