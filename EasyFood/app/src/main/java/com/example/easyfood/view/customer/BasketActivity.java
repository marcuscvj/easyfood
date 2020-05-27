package com.example.easyfood.view.customer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.model.totalPriceCalculator;
import com.example.easyfood.viewmodel.BasketViewModel;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends CustomerBaseActivity implements BasketAdapter.OnRemoveEateryListener {

    private RecyclerView recyclerView;
    private BasketViewModel viewModel;
    private BasketAdapter adapter;
    private totalPriceCalculator calculator = new totalPriceCalculator();
    private Button sendOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        sendOrder = findViewById(R.id.placeorder);


        recyclerView = findViewById(R.id.basket_recycleView);

        viewModel = new ViewModelProvider(this).get(BasketViewModel.class);
        viewModel.init();
        viewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
                updateTotalSum();
            }
        });
        setRecyclerView();
        setSendOrderButtonListener();


    }

    @Override
    public void onResume(){
        super.onResume();
        updateTotalSum();

    }

    /**
     * Sets the Recycler View (List) of all the products.
     */
    private void setRecyclerView() {
        adapter = new BasketAdapter(this, viewModel.getProducts().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRemoveEateryClick(int position) {
        viewModel.removeProduct(position);
    }

    public void updateTotalSum () {
        TextView totalSum = findViewById(R.id.total_sum);

        ArrayList<Product> listWithProducts = (ArrayList<Product>) viewModel.getProducts().getValue();

        totalSum.setText(String.format(String.valueOf(calculator.getTotalPriceOfProducts(listWithProducts))));

    }

    private void setSendOrderButtonListener() {
        sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOrder();
            }
        });
    }

    public void sendOrder() {

        if (viewModel.getProducts().getValue().size() == 0) {

            Toast.makeText(getApplicationContext(), R.string.empty_basket, Toast.LENGTH_SHORT).show();

        } else {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setCancelable(true);
            builder1.setMessage("Send order to eatery?");

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            TextView note = findViewById(R.id.note);
                            TextView totalSum = findViewById(R.id.total_sum);
                            viewModel.sendOrder(totalSum.getText().toString().trim(), note.getText().toString().trim());

                            note.setText("");

                            Toast.makeText(getApplicationContext(), R.string.order_sent,  Toast.LENGTH_SHORT).show();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }

    }
}
