package com.example.easyfood.view.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.easyfood.R;
import com.example.easyfood.viewmodel.ManagerMenuViewModel;

public class NewProductActivity extends ManagerBaseActivity {
    private ManagerMenuViewModel viewModel;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private EditText categoryEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_new);

        setEateryId();

        viewModel = new ViewModelProvider(this).get(ManagerMenuViewModel.class);
        viewModel.init(eateryId);

        nameEditText = findViewById(R.id.name_editText);
        descriptionEditText = findViewById(R.id.description_editText);
        priceEditText = findViewById(R.id.price_editText);
        categoryEditText = findViewById(R.id.category_editText);
        addButton = findViewById(R.id.add_button);

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
                String category = categoryEditText.getText().toString().toUpperCase().trim();
                String price = priceEditText.getText().toString().trim();

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

                if (category.isEmpty()) {
                    categoryEditText.setError("Enter Description!");
                    categoryEditText.requestFocus();
                    return;
                }

                if (price.isEmpty()) {
                    descriptionEditText.setError("Enter Price!");
                    descriptionEditText.requestFocus();
                    return;
                }

                addProduct(name, description, Double.parseDouble(price), category);
                Toast.makeText(getApplicationContext(), R.string.product_added, Toast.LENGTH_SHORT).show();
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
    private void addProduct(String name, String description, Double price, String category) {
        viewModel.createProduct(eateryId, name, description, price, category);
        Intent intent = new Intent(getApplicationContext(), ManagerMenuActivity.class);
        intent.putExtra("eateryId", eateryId);
        goToActivity(intent);
    }
}
