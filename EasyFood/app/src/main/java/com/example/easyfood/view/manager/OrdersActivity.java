package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.OrdersViewModel;

import java.util.List;

public class OrdersActivity extends ManagerBaseActivity implements OrdersAdapter.OnOrderListener {
    private RecyclerView recyclerView;
    private OrdersViewModel viewModel;
    private OrdersAdapter adapter;

    private String eateryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.orders_recycleView);

        getEateryId();

        viewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
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
        adapter = new OrdersAdapter(this, viewModel.getOrders().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnOrderClick(int position) {
        String orderId = viewModel.getOrder(position).getId();
        Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
        intent.putExtra("orderId", orderId);
        /**
        Order order = viewModel.getOrder(position);
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("order", order);
         **/
        startActivity(intent);
    }

    private void getEateryId() {
        Intent intent = getIntent();
        eateryId = intent.getStringExtra("eateryId");
    }
}
