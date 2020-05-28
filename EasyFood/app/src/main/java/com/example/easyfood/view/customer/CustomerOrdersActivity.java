package com.example.easyfood.view.customer;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.viewmodel.CustomerOrdersViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class CustomerOrdersActivity extends CustomerBaseActivity {

    private RecyclerView recyclerView;
    private CustomerOrdersViewModel viewModel;
    private CustomerOrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_customer);

        recyclerView = findViewById(R.id.orders_recycleView);

        getCustomerId();


        viewModel = new ViewModelProvider(this).get(CustomerOrdersViewModel.class);
        viewModel.init(customerId);
        viewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                adapter.setOrders(orders);
            }
        });


        setRecyclerView();

    }

    private void setRecyclerView() {
        adapter = new CustomerOrdersAdapter(this, viewModel.getOrders().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void getCustomerId() {
        customerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}