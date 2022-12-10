package com.example.app_room_livedata_viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.app_room_livedata_viewmodel.adapter.StudentAdapter;
import com.example.app_room_livedata_viewmodel.database.MyDatabase;
import com.example.app_room_livedata_viewmodel.entity.Student;
import com.example.app_room_livedata_viewmodel.viewmodel.StudentViewModel;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MyDatabase myDatabase;
    private List<Student> students;
    private StudentAdapter studentAdapter;
    private Context context;
    private Faker faker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        faker = new Faker();

        ListView listStudent = findViewById(R.id.listStudent);
        students = new ArrayList<>();
        studentAdapter = new StudentAdapter(context, students);
        listStudent.setAdapter(studentAdapter);

        myDatabase = MyDatabase.getInstance(context);
        StudentViewModel studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudents().observe(this, (students) -> {
            // 當 student 資料表紀錄有變更時要做的事
            this.students.clear();
            this.students.addAll(students);
            studentAdapter.notifyDataSetChanged();
        });

        // Add button
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            Student student = new Student(faker.name().firstName(), new Random().nextInt(20) + 20);
            myDatabase.studentDao().insert(student);
        });
    }
}