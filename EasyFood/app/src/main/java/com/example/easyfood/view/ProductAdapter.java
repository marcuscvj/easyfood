package com.example.easyfood.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private OnAddProductListener onAddProductListener;


    /**
     * Creates an instance of an ProductAdapter
     *
     * @param context: Context - The Context.
     * @param products: List<Product> - The list of products
     */
    public ProductAdapter(Context context, List<Product> products, OnAddProductListener onAddProductListener) {
        this.products = products;
        this.context = context;
        this.onAddProductListener = onAddProductListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_productlist, parent, false);
        return new ViewHolder(view, onAddProductListener);

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

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView desc;
        private TextView price;
        OnAddProductListener onAddProductListener;

        public ViewHolder(@NonNull View itemView, OnAddProductListener onAddProductListener) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
            this.onAddProductListener = onAddProductListener;
            Button button = itemView.findViewById(R.id.add_product_button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onAddProductListener.OnAddProductClick(getAdapterPosition());

        }
    }

    public interface OnAddProductListener {
        void OnAddProductClick(int position);
    }


}
