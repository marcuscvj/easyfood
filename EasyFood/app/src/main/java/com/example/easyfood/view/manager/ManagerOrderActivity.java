package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;
import com.example.easyfood.viewmodel.ManagerOrderViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manager Order Activity
 */
public class ManagerOrderActivity extends ManagerBaseActivity {
    private String orderId;
    private ManagerOrderViewModel viewModel;
    private Order.Status status;
    private boolean isPaid;
    private String paid = "Paid";
    private String notPaid = "Not yet paid";

    private TextView numberTextView;
    private TextView statusTextView;
    private TextView paymentMethodTextView;
    private TextView isPaidTextView;
    private TextView productTextView;

    private Spinner statusSpinner;
    private Spinner isPaidSpinner;
    private Button changeStatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manager);

        numberTextView = findViewById(R.id.order_number);
        statusTextView = findViewById(R.id.order_status);
        isPaidTextView = findViewById(R.id.order_isPaid);
        paymentMethodTextView = findViewById(R.id.order_paymentMethod);
        productTextView = findViewById(R.id.order_products);
        statusSpinner = findViewById(R.id.order_statusSpinner);
        isPaidSpinner = findViewById(R.id.order_isPaidSpinner);
        changeStatusButton = findViewById(R.id.changeStatus_button);

        setOrderId();
        setEateryId();

        viewModel = new ViewModelProvider(this).get(ManagerOrderViewModel.class);
        viewModel.init(orderId);
        viewModel.getOrder().observe(this, new Observer<Order>() {
            @Override
            public void onChanged(Order order) {
                setOrderDetails(order);
            }
        });

        setStatusSpinner();
        setIsPaidSpinner();
        setChangeStatusButtonListener();
    }

    /**
     * Sets the order id
     */
    private void setOrderId() {
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
    }

    /**
     * Sets the order details
     *
     * @param order : Order
     */
    private void setOrderDetails(Order order) {
        String orderNumber = "OrderNumber: ";
        String orderStatus = "OrderStatus: ";
        String paymentStatus = "PaymentStatus: ";
        String paymentMethod = "PaymentMethod: ";
        String products = "Products: ";

        numberTextView.setText(orderNumber + order.getOrderNumber());

        if (order.getOrderStatus() != null) {
            statusTextView.setText(orderStatus + order.getOrderStatus().getStatus());
            status = order.getOrderStatus();
        }

        if (order.isPaid()) {
            isPaidTextView.setText(paymentStatus + paid);
        } else {
            isPaidTextView.setText(paymentStatus + notPaid);
        }

        if (order.getPaymentMethod() != null) {
            paymentMethodTextView.setText(paymentMethod + order.getPaymentMethod().getPaymentMethod());
        }

        if (order.getProducts() != null) {
            String productString = products;
            for (Product p : order.getProducts()) {
                productString += "\n" + p.getName();
            }
            productTextView.setText(productString);
        }
    }

    /**
     * Sets the status spinner
     */
    private void setStatusSpinner() {
        List<Order.Status> list = new ArrayList<>(Arrays.asList(Order.Status.values()));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        statusSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = Order.Status.valueOf(adapterView.getItemAtPosition(i).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * Sets the isPaid spinner
     */
    private void setIsPaidSpinner() {
        List<String> list = new ArrayList<>(Arrays.asList(notPaid, paid));
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isPaidSpinner.setAdapter(adapter);

        isPaidSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i) == paid) {
                    isPaid = true;
                } else {
                    isPaid = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * Sets the status button
     */
    private void setChangeStatusButtonListener() {
        changeStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.updateOrderStatus(orderId, status);
                viewModel.updatePaymentStatus(orderId, isPaid);
            }
        });
    }
}
