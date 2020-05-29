package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.viewmodel.ManagerOrdersViewModel;

import java.util.List;

public class ManagerOrdersActivity extends ManagerBaseActivity implements ManagerOrdersAdapter.OnOrderListener {
    private RecyclerView recyclerView;
    private ManagerOrdersViewModel viewModel;
    private ManagerOrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_manager);

        recyclerView = findViewById(R.id.orders_recycleView);

        setEateryId();

        viewModel = new ViewModelProvider(this).get(ManagerOrdersViewModel.class);
        viewModel.init(eateryId);
        viewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                adapter.setOrders(orders);
            }
        });

        setRecyclerView();
    }

    private void setRecyclerView() {
        adapter = new ManagerOrdersAdapter(this, viewModel.getOrders().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnOrderClick(int position) {
        String orderId = viewModel.getOrder(position).getId();
        Intent intent = new Intent(getApplicationContext(), ManagerOrderActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("eateryId", eateryId);
        startActivity(intent);
    }
}
