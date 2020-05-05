package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easyfood.R;
import com.example.easyfood.view.BaseActivity;

/**
 * Main Customer Activity
 *
 * Eatery customer's main activity.
 */
public class MainCustomerActivity extends BaseActivity {
    private Button eateryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        eateryButton = findViewById(R.id.eatery_button);

        setEateryButtonListener();
    }

    /**
     * Sets the OnClickListener for the Eatery/Restaurants Button.
     */
    private void setEateryButtonListener() {
        eateryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eateryIntent = new Intent(getApplicationContext(), EateryActivity.class);
                startActivity(eateryIntent);
            }
        });
    }
}
