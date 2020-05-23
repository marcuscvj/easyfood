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

import java.util.List;

public class OrdersActivity extends BaseActivity implements OrdersAdapter.OnOrderListener {
    private RecyclerView recyclerView;
    private OrdersViewModel viewModel;
    private OrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.orders_recycleView);


        viewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        viewModel.init("testId"); // TODO Get Real Id
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
        Order order = viewModel.getOrder(position);
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}
