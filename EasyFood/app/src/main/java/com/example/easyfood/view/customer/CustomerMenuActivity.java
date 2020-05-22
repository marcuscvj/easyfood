package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.ProductAdapter;
import com.example.easyfood.viewmodel.CustomerMenuViewModel;

import java.util.List;

public class CustomerMenuActivity extends BaseActivity implements ProductAdapter.OnAddProductListener{

    private String restaurantID;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private CustomerMenuViewModel viewModel;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu);
        super.onCreate(savedInstanceState);
        getChosenRestaurant();

        recyclerView = findViewById(R.id.menu_recycleView);
        searchView = findViewById(R.id.menu_searchView);

        viewModel = new ViewModelProvider(this).get(CustomerMenuViewModel.class);
        viewModel.init(restaurantID);
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
        adapter = new ProductAdapter(this, viewModel.getProductsInMenu().getValue(), this);
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

    private void getChosenRestaurant() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        restaurantID = bundle.get("RestaurantID").toString();
    }

    @Override
    public void OnAddProductClick(int position) {
        Product chosenProduct = viewModel.getProductsInMenu().getValue().get(position);
        viewModel.addProduct(chosenProduct);

    }


}
