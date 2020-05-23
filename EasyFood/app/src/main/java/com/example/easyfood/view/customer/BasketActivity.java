package com.example.easyfood.view.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.model.totalPriceCalculator;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.BasketAdapter;
import com.example.easyfood.viewmodel.BasketViewModel;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends BaseActivity implements BasketAdapter.OnRemoveRestaurantListener {

    private RecyclerView recyclerView;
    private BasketViewModel viewModel;
    private RecyclerView.Adapter adapter;
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
                adapter.notifyDataSetChanged();
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
    public void onRemoveRestaurantClick(int position) {
        Product chosenProduct = viewModel.getProducts().getValue().get(position);
        viewModel.removeProduct(chosenProduct);
       updateTotalSum();
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
                TextView totalSum = findViewById(R.id.total_sum);
            viewModel.sendOrder(totalSum.getText().toString().trim());
            }
        });
    }
}
