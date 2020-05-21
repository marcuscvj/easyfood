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

public class ManagerMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context context;
    private OnRemoveProductListener onRemoveProductListener;

    public ManagerMenuAdapter(Context context, List<Product> products, OnRemoveProductListener onRemoveProductListener) {
        this.products = products;
        this.context = context;
        this.onRemoveProductListener = onRemoveProductListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_managermenulist, parent, false);
        return new ManagerMenuAdapter.ViewHolder(view, onRemoveProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ManagerMenuAdapter.ViewHolder) holder).name.setText(products.get(position).getName());
        ((ManagerMenuAdapter.ViewHolder) holder).desc.setText(products.get(position).getDescription());
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

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView desc;
        private TextView price;
        private OnRemoveProductListener onRemoveProductListener;

        public ViewHolder(@NonNull View itemView, OnRemoveProductListener onRemoveProductListener) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            desc = itemView.findViewById(R.id.product_description);
            price = itemView.findViewById(R.id.product_price);
            this.onRemoveProductListener = onRemoveProductListener;
            Button button = itemView.findViewById(R.id.remove_product_button);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRemoveProductListener.onRemoveProductClick(getAdapterPosition());
        }
    }

    public interface OnRemoveProductListener {
        void onRemoveProductClick(int position);

    }
}
