package com.example.easyfood.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Represents a ProductAdapter
 */
public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Product> products;
    private Context context;


    /**
     * Creates an instance of an ProductAdapter
     *
     * @param context: Context - The Context.
     * @param products: List<Product> - The list of products
     */
    public ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_productlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(products.get(position).getName());
        ((ViewHolder)holder).desc.setText(products.get(position).getDescription());

        double price = products.get(position).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        String result = decimalFormat.format(Double.valueOf(price));

        ((ViewHolder)holder).price.setText(result + " :-");

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView desc;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}
