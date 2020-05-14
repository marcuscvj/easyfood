package com.example.easyfood.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyfood.R;

public class RegisterActivity extends BaseActivity {

    TextView emailTextView;
    TextView passwordTextView;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailTextView = findViewById(R.id.email_textView);
        passwordTextView = findViewById(R.id.password_textView);
        registerButton = findViewById(R.id.register_button);
    }
}
