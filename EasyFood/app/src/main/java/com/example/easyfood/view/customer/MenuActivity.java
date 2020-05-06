package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.easyfood.R;
import com.example.easyfood.view.BaseActivity;

public class MenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        getChosenRestaurant();

    }

    private void getChosenRestaurant() {
        TextView test = findViewById(R.id.textView);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String restaurant = bundle.get("Restaurant").toString();
        test.setText("Menu for " + restaurant + " goes here.");
    }
}
