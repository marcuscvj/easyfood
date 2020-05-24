package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.OrderViewModel;

public class OrderActivity extends ManagerBaseActivity {
    private String orderId;
    private OrderViewModel viewModel;
    private TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tempTextView = findViewById(R.id.order_temp);

        getOrderId();

        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        viewModel.init(orderId);
        viewModel.getOrder().observe(this, new Observer<Order>() {
            @Override
            public void onChanged(Order order) {
                setOrderDetails(order);
            }
        });
    }

    private void getOrderId() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
    }

    private void setOrderDetails(Order order) {
        String orderString = "";
        orderString += "OrderId = " + order.getId() + "\n";
        orderString += "Is Paid = " + order.isPaid() + "\n";
        if (order.getProducts() != null) {
            for (Product p : order.getProducts()) {
                orderString += "Product = " + p.getName() + "\n";
            }
        }
        tempTextView.setText(orderString);
    }
}
