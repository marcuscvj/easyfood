package com.example.easyfood.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;
import com.example.easyfood.model.Eatery;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an EateryAdapter
 */
public class EateryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private List<Eatery> eateries;
    private List<Eatery> eateriesFull;
    private Context context;
    private OnRestaurantListener onRestaurantListener;

    /**
     * Creates an instance of an EateryAdapter
     *
     * @param context: Context - The Context.
     * @param eateries: List<Eatery> - The list of eateries.
     */
    public EateryAdapter(Context context, List<Eatery> eateries, OnRestaurantListener onRestaurantListener) {
        this.eateries = eateries;
        // this.eateriesTemp = new ArrayList<>();
        this.eateriesFull = new ArrayList<>();
        this.context = context;
        this.onRestaurantListener = onRestaurantListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_eaterylist, viewGroup, false);
        return new ViewHolder(view, onRestaurantListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(eateries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return eateries.size();
    }

    /**
     * Updates the RecyclerView with a new list of eateries.
     *
     * @param eateries : List<Eatery> - The list of eateries
     */
    public void setEateries(List<Eatery> eateries) {
        this.eateries = eateries;
        notifyDataSetChanged();
    }

    /**
     * Updates the backup eatery list
     *
     * @param eateries : List<Eatery> - The list of eateries
     */
    public void setEateriesFull(List<Eatery> eateries) {
        eateriesFull.addAll(eateries);
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
            List<Eatery> eateriesTemp = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence.toString().isEmpty()) {
                results.values = eateriesFull;
                results.count = eateriesFull.size();
            } else {
                String searchString = charSequence.toString().toLowerCase().trim();

                for (Eatery eatery : eateriesFull) {
                    if (eatery.getName().toLowerCase().contains(searchString)) {
                        eateriesTemp.add(eatery);
                    }
                }

                results.values = eateriesTemp;
                results.count = eateriesTemp.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults.count > 0) {
                setEateries((List<Eatery>) filterResults.values);
            } else {
                List<Eatery> empty = new ArrayList<>();
                setEateries(empty);
            }
        }
    };

    /**
     * ViewHolder Class
     */
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        OnRestaurantListener onRestaurantListener;

        /**
         * Creates an instance of a ViewHolder
         *
         * @param itemView: View - The view
         */
        public ViewHolder(@NonNull View itemView, OnRestaurantListener onRestaurantListener) {
            super(itemView);
            name = itemView.findViewById(R.id.eatery_name);
            this.onRestaurantListener = onRestaurantListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRestaurantListener.OnRestaurantClick(getAdapterPosition());
        }
    }

    public interface OnRestaurantListener {
        void OnRestaurantClick(int position);
    }
}
