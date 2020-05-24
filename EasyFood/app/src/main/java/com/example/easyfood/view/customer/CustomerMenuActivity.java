package com.example.easyfood.view.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.CustomerMenuViewModel;

import java.util.List;

public class CustomerMenuActivity extends CustomerBaseActivity implements CustomerMenuAdapter.OnAddProductListener{

    private String eateryId;

    private TextView menuHeader;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CustomerMenuViewModel viewModel;
    private CustomerMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);

        menuHeader = findViewById(R.id.menu_header);
        recyclerView = findViewById(R.id.menu_recycleView);
        searchView = findViewById(R.id.menu_searchView);

        getEateryInfo();

        viewModel = new ViewModelProvider(this).get(CustomerMenuViewModel.class);
        viewModel.init(eateryId);
        viewModel.getProductsInMenu().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
                adapter.setProductsFull(products);
            }
        });

        setRecyclerView();
        setSearchView();

    }

    /**
     * Sets the Recycler View (List) of all the products.
     */
    private void setRecyclerView() {
        adapter = new CustomerMenuAdapter(this, viewModel.getProductsInMenu().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Sets the Search View.
     */
    private void setSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    /**
     * Gets the eatery id from the intent
     */
    private void getEateryInfo() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        eateryId = extras.getString("eateryId");

        String contactInformation = extras.getString("name") + "\n";
        contactInformation += "Address: " + extras.getString("street") + " " + extras.getString("streetNumber") + "\n";
        contactInformation += "Phone: " + extras.getString("phoneNumber") + "\n";
        contactInformation += "openingHours: " + extras.getString("openingHours");


        menuHeader.setText(contactInformation);
    }

    @Override
    public void OnAddProductClick(int position) {
        Context context = getApplicationContext();
        viewModel.addProduct(position, context);
    }


}
