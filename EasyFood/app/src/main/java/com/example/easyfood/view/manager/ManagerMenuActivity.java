package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.view.ProductAdapter;
import com.example.easyfood.viewmodel.ProductActivityViewModel;

import java.util.List;

public class ManagerMenuActivity extends BaseActivity {
    private String restaurantID;

    private Button newProductButton;

    private RecyclerView recyclerView;
    private ProductActivityViewModel viewModel;
    private RecyclerView.Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_manager);

        getChosenRestaurant();

        newProductButton = findViewById(R.id.new_product_button);

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
        setNewProductButtonListener();

    }

    /**
     * Sets the Recycler View (List) of all the products.
     */
    private void setRecyclerView() {                 // TODO Make own adapter
        adapter = new ProductAdapter(this, viewModel.getProducts().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getChosenRestaurant() {             // TODO Get the right id
        restaurantID = "XvcEDPdTRl5i8IQuoIEh";
    }

    /**
     * Sets the OnClickListener for the New Product Button.
     */
    private void setNewProductButtonListener() {
        newProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(new Intent(getApplicationContext(), NewProductActivity.class));
            }
        });
    }


}
