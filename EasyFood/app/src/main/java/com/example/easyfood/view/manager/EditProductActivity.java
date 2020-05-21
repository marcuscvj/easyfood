package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.view.BaseActivity;
import com.example.easyfood.viewmodel.ManagerMenuViewModel;

public class EditProductActivity extends BaseActivity {
    private ManagerMenuViewModel viewModel;
    private String restaurantId;
    private String productId;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_new);

        getRestaurantId();

        viewModel = new ViewModelProvider(this).get(ManagerMenuViewModel.class);

        nameEditText = findViewById(R.id.name_editText);
        descriptionEditText = findViewById(R.id.description_editText);
        priceEditText = findViewById(R.id.price_editText);
        addButton = findViewById(R.id.add_button);
        addButton.setText("Update");

        getExtras();

        setAddButtonListener();

    }

    /**
     * Sets the On Click Listener for Add Button.
     */
    private void setAddButtonListener() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();
                Double price = Double.valueOf(priceEditText.getInputType()); // TODO FIX BUG, Price is always set to 8194:-

                if (name.isEmpty()) {
                    nameEditText.setError("Provide a name first!");
                    nameEditText.requestFocus();
                    return;
                }

                if (description.isEmpty()) {
                    descriptionEditText.setError("Enter Description!");
                    descriptionEditText.requestFocus();
                    return;
                }

                if (price.isNaN()) {
                    descriptionEditText.setError("Enter Price!");
                    descriptionEditText.requestFocus();
                    return;
                }

                addProduct(name, description, price);
            }
        });
    }

    /**
     * Adds a new product to the menu
     *
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     */
    private void addProduct(String name, String description, Double price) {
        viewModel.updateProduct(restaurantId, name, description, price);
        goToActivity(new Intent(getApplicationContext(), ManagerMenuActivity.class));
    }

    private void getRestaurantId() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        restaurantId = extras.getString("RestaurantId");
    }

    /**
     * Gets the current Eatery.
     */
    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        productId = extras.getString("id");
        nameEditText.setText(extras.getString("name"));
        descriptionEditText.setText(extras.getString("desc"));


    }
}
