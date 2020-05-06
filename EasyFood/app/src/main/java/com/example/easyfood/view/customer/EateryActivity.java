package com.example.easyfood.view.customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Eatery;
import com.example.easyfood.model.EateryAdapter;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.EateryActivityViewModel;

import java.util.List;

/**
 * Eatery Activity
 *
 * TEMPORARY:
 * Contains a list of all the available restaurants.
 */
public class EateryActivity extends BaseActivity implements EateryAdapter.OnRestaurantListener {
    private RecyclerView recyclerView;
    private EateryActivityViewModel viewModel;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatery);

        recyclerView = findViewById(R.id.eatery_recycleView);

        viewModel = new ViewModelProvider(this).get(EateryActivityViewModel.class);
        viewModel.init();
        viewModel.getEateries().observe(this, new Observer<List<Eatery>>() {
            @Override
            public void onChanged(List<Eatery> eateries) {
                adapter.notifyDataSetChanged();
            }
        });

        setRecyclerView();
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

    @Override
    public void OnRestaurantClick(int position) {
        String chosenRestaurant = viewModel.getEateries().getValue().get(position).getName();
    Intent intent = new Intent(this, MenuActivity.class);
    intent.putExtra("Restaurant", chosenRestaurant);
    startActivity(intent);
    }
}
