package com.example.easyfood.view.manager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;

public class OrderActivity extends BaseActivity {
    private TextView tempTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // TODO Code below is just used for testing!, The order should not be passed from the previous intent.

        tempTextView = findViewById(R.id.order_temp);

        Order order;

        order = (Order) getIntent().getSerializableExtra("order");

        String orderString = "";

        orderString += "OrderId = " + order.getId() + "\n";
        orderString += "Is Paid = " + order.isPaid() + "\n";


        for (Product p : order.getProducts()) {
            orderString += "Product = " + p.getName() + "\n";
        }


        tempTextView.setText(orderString);

    }
}
