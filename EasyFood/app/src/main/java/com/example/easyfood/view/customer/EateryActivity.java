package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Eatery;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.EateryActivityViewModel;

import java.util.List;

/**
 * Eatery Activity
 *
 * TEMPORARY:
 * Contains a list of all the available eateries.
 */
public class EateryActivity extends BaseActivity implements EateryAdapter.OnEateryListener {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private EateryActivityViewModel viewModel;
    private EateryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery);

        recyclerView = findViewById(R.id.eatery_recycleView);
        searchView = findViewById(R.id.eatery_searchView);

        viewModel = new ViewModelProvider(this).get(EateryActivityViewModel.class);
        viewModel.init();
        viewModel.getEateries().observe(this, new Observer<List<Eatery>>() {
            @Override
            public void onChanged(List<Eatery> eateries) {
                adapter.setEateries(eateries);
                adapter.setEateriesFull(eateries);
            }
        });

        setRecyclerView();
        setSearchView();
    }

    /**
     * Sets the Recycler View (List) of all the eateries.
     */
    private void setRecyclerView() {
        adapter = new EateryAdapter(this, viewModel.getEateries().getValue(), this);
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

    @Override
    public void OnEateryClick(int position) {
        Eatery eatery = viewModel.getEateries().getValue().get(position);

        Bundle extras = new Bundle();
        extras.putString("eateryId", eatery.getId());
        extras.putString("name", eatery.getName());
        extras.putInt("phoneNumber", eatery.getPhoneNumber());
        extras.putString("street", eatery.getStreet());
        extras.putInt("streetNumber", eatery.getStreetNumber());
        extras.putInt("postalCode", eatery.getPostalCode());
        extras.putString("city", eatery.getCity());
        extras.getString("openingHours", eatery.getOpeningHours());

        Intent intent = new Intent(this, CustomerMenuActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
