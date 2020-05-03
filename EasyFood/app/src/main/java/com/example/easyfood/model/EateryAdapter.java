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

public class EateryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Eatery> eateries;
    private Context context;

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

    private class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.eatery_name);
        }
    }
}
