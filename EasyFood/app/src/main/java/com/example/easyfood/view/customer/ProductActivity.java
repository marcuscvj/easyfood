package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.ProductAdapter;
import com.example.easyfood.view.RegisterActivity;
import com.example.easyfood.viewmodel.BasketActivityViewModel;
import com.example.easyfood.viewmodel.ProductActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends BaseActivity implements ProductAdapter.OnAddProductListener{

    private String restaurantID;
    private RecyclerView recyclerView;
    private ProductActivityViewModel viewModel;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        getChosenRestaurant();

        recyclerView = findViewById(R.id.menu_recycleView);

        viewModel = new ViewModelProvider(this).get(ProductActivityViewModel.class);
        viewModel.init(restaurantID);
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
        adapter = new ProductAdapter(this, viewModel.getProducts().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getChosenRestaurant() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        restaurantID = bundle.get("RestaurantID").toString();
    }

    @Override
    public void OnAddProductClick(int position) {
        Product chosenProduct = viewModel.getProducts().getValue().get(position);
        viewModel.addProduct(chosenProduct);
    }


}
