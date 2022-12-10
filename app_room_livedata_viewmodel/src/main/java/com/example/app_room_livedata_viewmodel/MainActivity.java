package com.example.app_room_livedata_viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import com.example.app_room_livedata_viewmodel.database.MyDatabase;
import com.example.app_room_livedata_viewmodel.entity.Student;
import com.example.app_room_livedata_viewmodel.viewmodel.StudentViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDatabase myDatabase;
    private List<Student> students;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        myDatabase = MyDatabase.getInstance(context);
        StudentViewModel studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudents().observe(this, (students) -> {

        });
    }
}