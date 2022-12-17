package com.example.app_databinding_recyclerview_room.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_databinding_recyclerview_room.databinding.LayoutItemBinding;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LayoutItemBinding layoutItemBinding;

        public MyViewHolder(LayoutItemBinding itemView) {
            super(itemView.getRoot());
            layoutItemBinding = itemView;
        }
    }

}
