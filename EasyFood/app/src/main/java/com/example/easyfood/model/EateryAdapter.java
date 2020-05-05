package com.example.easyfood.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyfood.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an EateryAdapter
 */
public class EateryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Eatery> eateries;
    private Context context;

    /**
     * Creates an instance of an EateryAdapter
     *
     * @param context: Context - The Context.
     * @param eateries: List<Eatery> - The list of eateries.
     */
    public EateryAdapter(Context context, List<Eatery> eateries) {
        this.eateries = eateries;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_eaterylist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(eateries.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return eateries.size() ;
    }

    /**
     * ViewHolder Class
     */
    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        /**
         * Creates an instance of a ViewHolder
         *
         * @param itemView: View - The view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eatery_name);
        }
    }
}
