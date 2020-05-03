package com.example.easyfood.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easyfood.R;
import com.example.easyfood.view.customer.CustomerActivity;
import com.example.easyfood.viewmodel.EateryActivityViewModel;

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

    private void setLoginButtonListener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerActivity = new Intent(getApplicationContext(), CustomerActivity.class);
                startActivity(customerActivity);
            }
        });
    }
}
