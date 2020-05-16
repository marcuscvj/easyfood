package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.easyfood.R;
import com.example.easyfood.view.customer.EateryActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Main Activity
 *
 * TEMPORARY:
 * Contains a Login and a Register Button.
 */
public class MainActivity extends BaseActivity implements FirebaseAuth.AuthStateListener {
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

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
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            Toast.makeText(getApplicationContext(), user.getEmail(), Toast.LENGTH_SHORT).show(); // DEBUGGING

            Intent I = new Intent(getApplicationContext(), EateryActivity.class);
            startActivity(I);
        } else {
            Toast.makeText(getApplicationContext(), "Login to continue", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sets the OnClickListener for the Login Button.
     */
    private void setLoginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Implement authentication service.
                Intent Activity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(Activity);
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
                Intent Activity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(Activity);
            }
        });
    }
}
