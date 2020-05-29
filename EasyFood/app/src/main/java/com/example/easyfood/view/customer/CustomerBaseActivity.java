package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.easyfood.R;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.MainActivity;


public class CustomerBaseActivity extends BaseActivity {
    protected String customerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucustomer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ic_exit) {
            firebaseAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
        } else if (item.getItemId() == R.id.ic_cart) {
            startActivity(new Intent(this, BasketActivity.class));
        } else if (item.getItemId() == R.id.ic_account) {
            startActivity(new Intent(this, CustomerSettingsActivity.class));
        } else if (item.getItemId() == R.id.ic_home) {
            startActivity(new Intent(this, EateryActivity.class));
        } if (item.getItemId() == R.id.ic_dining) {
            startActivity(new Intent(this, CustomerOrdersActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
