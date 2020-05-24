package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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
    private Order.Status status;

    private TextView numberTextView;
    private TextView statusTextView;
    private TextView isPaidTextView;
    private TextView productTextView;

    private Spinner statusListView;
    private Button changeStatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        numberTextView = findViewById(R.id.order_number);
        statusTextView = findViewById(R.id.order_status);
        isPaidTextView = findViewById(R.id.order_isPaid);
        productTextView = findViewById(R.id.order_products);
        statusListView = findViewById(R.id.order_statusList);
        changeStatusButton = findViewById(R.id.changeStatus_button);

        getOrderId();

        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        viewModel.init(orderId);
        viewModel.getOrder().observe(this, new Observer<Order>() {
            @Override
            public void onChanged(Order order) {
                setOrderDetails(order);
            }
        });

        setStatusListView();
        setChangeStatusButtonListener();
    }

    private void getOrderId() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
    }

    // TODO Could be improved
    private void setOrderDetails(Order order) {
        String orderNumber = "OrderNumber: ";
        String orderStatus = "OrderStatus: ";
        String paymentStatus = "PaymentStatus: ";
        String isPaid = "Yes";
        String isNotPaid = "Not yet paid";
        String products = "Products: ";

        numberTextView.setText(orderNumber + order.getOrderNumber());

        if (order.getOrderStatus() != null) {
            statusTextView.setText(orderStatus + order.getOrderStatus().getStatus());
            status = order.getOrderStatus();
        }

        if (order.isPaid()) {
            isPaidTextView.setText(paymentStatus + isPaid);
        } else {
            isPaidTextView.setText(paymentStatus + isNotPaid);
        }

        if (order.getProducts() != null) {
            String productString = products;
            for (Product p : order.getProducts()) {
                productString += "\n" + p.getName();
            }
            productTextView.setText(productString);
        }
    }

    private void setStatusListView() {
        List<Order.Status> list = new ArrayList<>(Arrays.asList(Order.Status.values()));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusListView.setAdapter(adapter);

        statusListView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = Order.Status.valueOf(adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void setChangeStatusButtonListener() {
        changeStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.updateOrderStatus(orderId, status);
            }
        });
    }
}
