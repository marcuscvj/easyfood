package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.Eatery;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.ManagerMainViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManagerMainActivity extends BaseActivity {
    private ManagerMainViewModel viewModel;

    private String restaurantId;

    private Button menuButton;
    private Button ordersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();

        viewModel = new ViewModelProvider(this).get(ManagerMainViewModel.class);
        viewModel.init(uid);
        viewModel.getUser().observe(this, new Observer<Eatery>() {
            @Override
            public void onChanged(Eatery eatery) {
                restaurantId = eatery.getId();
            }
        });

        menuButton = findViewById(R.id.menu_button);
        ordersButton = findViewById(R.id.orders_button);

        setMenuButtonListener();
        setOrdersButtonListener();
    }

    /**
     * Sets the OnClickListener for the Menu Button.
     */
    private void setMenuButtonListener() {
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManagerMenuActivity.class);
                intent.putExtra("restaurantId", restaurantId);
                goToActivity(intent);
            }
        });
    }

    private void setOrdersButtonListener() {
        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrdersActivity.class);
                intent.putExtra("restaurantId", restaurantId);
                goToActivity(intent);
            }
        });
    }
}
