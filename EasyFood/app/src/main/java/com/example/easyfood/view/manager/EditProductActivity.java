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

public class EditProductActivity extends ManagerBaseActivity {
    private ManagerMenuViewModel viewModel;
    private String eateryId;
    private String productId;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText priceEditText;
    private EditText categoryEditText;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_new);

        nameEditText = findViewById(R.id.name_editText);
        descriptionEditText = findViewById(R.id.description_editText);
        categoryEditText = findViewById(R.id.category_editText);
        priceEditText = findViewById(R.id.price_editText);
        editButton = findViewById(R.id.add_button);
        editButton.setText("Update");

        getExtras();

        viewModel = new ViewModelProvider(this).get(ManagerMenuViewModel.class);
        viewModel.init(eateryId);

        setEditButtonListener();

    }

    /**
     * Sets the On Click Listener for Add Button.
     */
    private void setEditButtonListener() {
        editButton.setOnClickListener(new View.OnClickListener() {
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

                editProduct(name, description, Double.parseDouble(price), category);
            }
        });
    }

    /**
     * Edits the chosen menu product
     *
     * @param name : String - The name of the product
     * @param description : String - The description of the product
     * @param price : Double - The price of the product
     */
    private void editProduct(String name, String description, Double price, String category) {
        viewModel.updateProduct(eateryId, productId, name, description, price, category);
        Intent intent = new Intent(getApplicationContext(), ManagerMenuActivity.class);
        intent.putExtra("eateryId", eateryId);
        goToActivity(intent);
    }

    /**
     * Gets the extras
     */
    private void getExtras() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        eateryId = extras.getString("eateryId");
        productId = extras.getString("id");
        nameEditText.setText(extras.getString("name"));
        descriptionEditText.setText(extras.getString("desc"));
        categoryEditText.setText(extras.getString("category"));
        priceEditText.setText(String.valueOf(extras.getDouble("price")));
    }
}
