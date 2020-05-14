package com.example.easyfood.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyfood.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends BaseActivity {
    FirebaseAuth firebaseAuth;

    TextView emailTextView;
    TextView passwordTextView;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        emailTextView = findViewById(R.id.email_textView);
        passwordTextView = findViewById(R.id.password_textView);
        registerButton = findViewById(R.id.register_button);

        setRegisterButtonListener();
    }

    private void setRegisterButtonListener() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailTextView.getText().toString().trim();
                String password = passwordTextView.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    emailTextView.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    passwordTextView.setError("Password is Required");
                    return;
                }

                if(password.length() < 6) {
                    passwordTextView.setError("Password must be at least 6 characters");
                }
            }
        });
    }
}
