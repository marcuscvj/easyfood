package com.example.easyfood.view.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Product;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a ProductAdapter
 */
public class CustomerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private List<Product> products;
    private List<Product> productsFull;
    private Context context;
    private OnAddProductListener onAddProductListener;

    /**
     * Creates an instance of an ProductAdapter
     *
     * @param context: Context - The Context.
     * @param products: List<Product> - The list of products
     */
    CustomerMenuAdapter(Context context, List<Product> products, OnAddProductListener onAddProductListener) {
        this.products = products;
        this.productsFull = new ArrayList<>();
        this.context = context;
        this.onAddProductListener = onAddProductListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_menu_customer, parent, false);
        return new ViewHolder(view, onAddProductListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(products.get(position).getName());
        ((ViewHolder)holder).desc.setText(products.get(position).getDescription());
        ((ViewHolder)holder).category.setText(products.get(position).getCategory());


        double price = products.get(position).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        String result = decimalFormat.format(Double.valueOf(price));

        ((ViewHolder)holder).price.setText(result + " :-");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * Sets products
     *
     * @param products : List<Product>
     */
    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    /**
     * Sets products full
     *
     * @param products : List<Product>
     */
    void setProductsFull(List<Product> products) {
        this.productsFull.addAll(products);
    }

    @Override
    public Filter getFilter() {
        return eateryFilter;
    }

    /**
     * Custom Eatery Filter
     */
    private Filter eateryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Product> productsTemp = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence.toString().isEmpty()) {
                results.values = productsFull;
                results.count = productsFull.size();
            } else {
                String searchString = charSequence.toString().toLowerCase().trim();

                for (Product product : productsFull) {
                    if (product.getName().toLowerCase().contains(searchString)) {
                        productsTemp.add(product);
                    }
                }

                results.values = productsTemp;
                results.count = productsTemp.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.count > 0) {
                setProducts((List<Product>) filterResults.values);
            } else {
                List<Product> empty = new ArrayList<>();
                setProducts(empty);
            }
        }
    };

    /**
     * Product Listener Interface
     */
    public interface OnAddProductListener {
        void OnAddProductClick(int position);
    }

    /**
     * Represents a View Holder
     */
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name;
        private TextView desc;
        private TextView price;
        private TextView category;
        OnAddProductListener onAddProductListener;

        ViewHolder(@NonNull View itemView, OnAddProductListener onAddProductListener) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_description);
            category = itemView.findViewById(R.id.product_category);
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
}
