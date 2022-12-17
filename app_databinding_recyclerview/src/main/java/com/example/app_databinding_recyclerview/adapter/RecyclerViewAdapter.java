package com.example.app_databinding_recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_databinding_recyclerview.R;
import com.example.app_databinding_recyclerview.databinding.LayoutItemBinding;
import com.example.app_databinding_recyclerview.model.Book;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<Book> books;

    public RecyclerViewAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 因為是 databinding 的機制所以要使用 DataBindingUtil.inflate
        LayoutItemBinding layoutItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_item,
                parent,
                false);

        return new MyViewHolder(layoutItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = books.get(position);
        holder.layoutItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // 因為是對應到 layout_item.xml 所以會自動生成 LayoutItemBinding 的物件
        LayoutItemBinding layoutItemBinding;
        public MyViewHolder(LayoutItemBinding itemView) {
            super(itemView.getRoot()); // getRoot() 返回布局文件的最外層
            layoutItemBinding = itemView;
        }
    }

}
