package com.example.easyfood.view.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;
import com.example.easyfood.model.Product;

import java.util.List;

public class CustomerOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Order> orders;
    private Context context;

    /**
     * Creates an instance of an OrderAdapter
     *
     * @param context: Context - The Context.
     * @param orders: List<Order> - The list of orders.
     */
    public CustomerOrdersAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_customerorders, viewGroup, false);
        return new CustomerOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CustomerOrdersAdapter.ViewHolder)holder).orderId.setText(orders.get(position).getOrderNumber());
        ((CustomerOrdersAdapter.ViewHolder)holder).status.setText(orders.get(position).getOrderStatus().toString());
        String products = "";
        for (Product p : orders.get(position).getProducts()) {
            products += "\n" + p.getName();
        }
        ((CustomerOrdersAdapter.ViewHolder)holder).productsList.setText(products);
        ((CustomerOrdersAdapter.ViewHolder)holder).time.setText(orders.get(position).getEstimatedTime());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    /**
     * Updates the RecyclerView with a new list of orders.
     *
     * @param orders : List<Eatery> - The list of orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder Class
     */
    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderId;
        private TextView status;
        private TextView productsList;
        private TextView time;
        /**
         * Creates an instance of a ViewHolder
         *
         * @param itemView: View - The view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId_textView);
            status = itemView.findViewById(R.id.status_textView);
            productsList = itemView.findViewById(R.id.products_textView);
            time = itemView.findViewById(R.id.time_textView);
        }

    }

}
