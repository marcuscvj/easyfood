package com.example.easyfood.view.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Order;

import java.util.List;

/**
 * Manager Orders Adapter
 */
public class ManagerOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Order> orders;
    private Context context;
    private OnOrderListener onOrderListener;

    /**
     * Creates an instance of an OrderAdapter
     *
     * @param context: Context - The Context.
     * @param orders: List<Order> - The list of orders.
     */
    public ManagerOrdersAdapter(Context context, List<Order> orders, OnOrderListener onOrderListener) {
        this.orders = orders;
        this.context = context;
        this.onOrderListener = onOrderListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_orders_manager, viewGroup, false);
        return new ViewHolder(view, onOrderListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).orderNumber.setText("Number: " + orders.get(position).getOrderNumber());
        ((ViewHolder)holder).orderStatus.setText("Status: " + orders.get(position).getOrderStatus().getStatus());
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
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView orderNumber;
        private TextView orderStatus;
        OnOrderListener onOrderListener;

        /**
         * Creates an instance of a ViewHolder
         *
         * @param itemView: View - The view
         */
        public ViewHolder(@NonNull View itemView, OnOrderListener onOrderListener) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.orderNumber_textView);
            orderStatus = itemView.findViewById(R.id.orderStatus_textView);
            this.onOrderListener = onOrderListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onOrderListener.OnOrderClick(getAdapterPosition());
        }
    }

    /**
     * Listening on order
     */
    public interface OnOrderListener {
        void OnOrderClick(int position);
    }
}
