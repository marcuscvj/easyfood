package com.example.easyfood.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Base Activity
 *
 * TEMPORARY:
 * The Base Activity could be a Master Class for all/some activities that share functionality
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
