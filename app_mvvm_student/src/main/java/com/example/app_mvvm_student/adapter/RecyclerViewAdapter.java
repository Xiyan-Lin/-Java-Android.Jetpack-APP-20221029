package com.example.app_mvvm_student.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_mvvm_student.R;
import com.example.app_mvvm_student.databinding.LayoutItemBinding;
import com.example.app_mvvm_student.model.Student;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    // 因為 RecyclerView 沒有實作 ItemClickListener
    // 所以我們可以自行在 MyViewHolder 中實現
    public interface MyItemClickListener {
        void onItemClick(int position, View view);
    }
    public interface MyItemLongClickListener {
        void onItemLongClick(int position, View view);
    }

    private MyItemClickListener myItemClickListener;
    private MyItemLongClickListener myItemLongClickListener;

    private List<Student> students;

    public RecyclerViewAdapter(List<Student> students) {
        this.students = students;
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public void setMyItemLongClickListener(MyItemLongClickListener myItemLongClickListener) {
        this.myItemLongClickListener = myItemLongClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemBinding layoutItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.layout_item, parent, false);
        return new MyViewHolder(layoutItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student = students.get(position);
        holder.layoutItemBinding.setStudent(student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        LayoutItemBinding layoutItemBinding;

        public MyViewHolder(@NonNull LayoutItemBinding itemView) {
            super(itemView.getRoot());
            layoutItemBinding = itemView;
            if (myItemClickListener != null) {
                itemView.getRoot().setOnClickListener(this);
            }
            if (myItemLongClickListener != null) {
                itemView.getRoot().setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if(myItemClickListener != null) {
                myItemClickListener.onItemClick(getAdapterPosition(), view);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if(myItemLongClickListener != null) {
                myItemLongClickListener.onItemLongClick(getAdapterPosition(), view);
            }
            return true;
        }
    }



}
