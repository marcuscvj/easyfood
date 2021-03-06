package com.example.easyfood.view.manager;

import android.content.Context;
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
 * Manager Menu Adapter
 */
public class ManagerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context context;
    private OnClickProductListener onClickProductListener;

    ManagerMenuAdapter(Context context, List<Product> products, OnClickProductListener onClickProductListener) {
        this.products = products;
        this.context = context;
        this.onClickProductListener = onClickProductListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_menu_manager, parent, false);
        return new ManagerMenuAdapter.ViewHolder(view, onClickProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ManagerMenuAdapter.ViewHolder) holder).name.setText(products.get(position).getName());
        ((ManagerMenuAdapter.ViewHolder) holder).desc.setText(products.get(position).getDescription());
        ((ManagerMenuAdapter.ViewHolder) holder).category.setText(products.get(position).getCategory());
        double price = products.get(position).getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.#####");
        String result = decimalFormat.format(Double.valueOf(price));

        ((ManagerMenuAdapter.ViewHolder) holder).price.setText(result + " :-");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    /**
     * Sets the product list
     *
     * @param products : List<Product> - The list of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    /**
     * Represents a View Holder
     */
    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView desc;
        private TextView price;
        private TextView category;

        ViewHolder(@NonNull View itemView, final OnClickProductListener onClickProductListener) {
            super(itemView);
            Button removeButton;
            Button editButton;

            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
            category = itemView.findViewById(R.id.product_category);
            removeButton = itemView.findViewById(R.id.remove_product_button);
            editButton = itemView.findViewById(R.id.edit_product_button);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickProductListener.onRemoveProductClick(getAdapterPosition());
                }
            });
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickProductListener.onEditProductClick(getAdapterPosition());
                }
            });
        }
    }

    /**
     * Interface
     */
    public interface OnClickProductListener {
        void onRemoveProductClick(int position);
        void onEditProductClick(int position);
    }
}
