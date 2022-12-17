package com.example.app_databinding_recyclerview_room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.app_databinding_recyclerview_room.adapter.RecyclerViewAdapter;
import com.example.app_databinding_recyclerview_room.databinding.ActivityMainBinding;
import com.example.app_databinding_recyclerview_room.model.Student;
import com.example.app_databinding_recyclerview_room.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ActivityMainBinding activityMainBinding;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Student> students;
    private StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        context = this;
        students = new ArrayList<>();

        // data binding
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        activityMainBinding.recyclerView.setHasFixedSize(true);

        // recyclerview's adapter
        recyclerViewAdapter = new RecyclerViewAdapter(students);
        activityMainBinding.recyclerView.setAdapter(recyclerViewAdapter);

        // ViewModel
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudents().observe(this, (students) -> {
            students.clear();
            students.addAll(students);
            recyclerViewAdapter.notifyDataSetChanged();
        });

    }
}