package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.BasketAdapter;
import com.example.easyfood.view.ProductAdapter;
import com.example.easyfood.viewmodel.BasketActivityViewModel;
import com.example.easyfood.viewmodel.EateryActivityViewModel;
import com.example.easyfood.viewmodel.ProductActivityViewModel;

import java.util.List;

public class BasketActivity extends BaseActivity implements BasketAdapter.OnRemoveRestaurantListener {

    private String restaurantID;
    private RecyclerView recyclerView;
    private BasketActivityViewModel viewModel;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        recyclerView = findViewById(R.id.basket_recycleView);

        viewModel = new ViewModelProvider(this).get(BasketActivityViewModel.class);
        viewModel.init();
        viewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.notifyDataSetChanged();
            }
        });

        setRecyclerView();

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

    }
}
