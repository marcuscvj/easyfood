package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.ManagerMenuViewModel;

import java.util.List;

public class ManagerMenuActivity extends BaseActivity implements ManagerMenuAdapter.OnClickProductListener {
    private String restaurantId;

    private Button newProductButton;

    private RecyclerView recyclerView;
    private ManagerMenuViewModel viewModel;
    private ManagerMenuAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manager);

        getRestaurantId();

        newProductButton = findViewById(R.id.new_product_button);

        recyclerView = findViewById(R.id.menu_recycleView);

        viewModel = new ViewModelProvider(this).get(ManagerMenuViewModel.class);
        Toast.makeText(this, restaurantId, Toast.LENGTH_SHORT).show();
        viewModel.init(restaurantId);
        viewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setProducts(products);
            }
        });

        setRecyclerView();
        setNewProductButtonListener();

    }

    /**
     * Sets the Recycler View (List) of all the products.
     */
    private void setRecyclerView() {                 // TODO Make own adapter
        adapter = new ManagerMenuAdapter(this, viewModel.getAllProducts().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getRestaurantId() {
        Intent intent = getIntent();
        restaurantId = intent.getStringExtra("restaurantId");
    }

    /**
     * Sets the OnClickListener for the New Product Button.
     */
    private void setNewProductButtonListener() {
        newProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewProductActivity.class);
                intent.putExtra("restaurantId", restaurantId);
                goToActivity(intent);
            }
        });
    }


    @Override
    public void onRemoveProductClick(int position) {
        Product chosenProduct = viewModel.getAllProducts().getValue().get(position);
        viewModel.removeProduct(restaurantId, chosenProduct.getId());
    }

    @Override
    public void onEditProductClick(int position) {
        Product chosenProduct = viewModel.getAllProducts().getValue().get(position);
        Intent intent = new Intent(getApplicationContext(), EditProductActivity.class);
        Bundle extras = new Bundle();
        extras.putString("restaurantId", restaurantId);
        extras.putString("id", chosenProduct.getId());
        extras.putString("name", chosenProduct.getName());
        extras.putString("desc", chosenProduct.getDescription());
        extras.putDouble("price", chosenProduct.getPrice());
        intent.putExtras(extras);
        goToActivity(intent);
    }
}
