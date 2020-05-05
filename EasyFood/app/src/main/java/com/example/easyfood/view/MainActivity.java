package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easyfood.R;
import com.example.easyfood.view.customer.MainCustomerActivity;

/**
 * Main Activity
 *
 * TEMPORARY:
 * Contains a Login and a Register Button.
 */
public class MainActivity extends BaseActivity {
    private Button loginButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);

        setLoginButtonListener();
    }

    /**
     * Sets the OnClickListener for the Login Button.
     */
    private void setLoginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Implement authentication service.
                Intent customerActivity = new Intent(getApplicationContext(), MainCustomerActivity.class);
                startActivity(customerActivity);
            }
        });
    }
}
