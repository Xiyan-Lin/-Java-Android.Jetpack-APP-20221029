package com.example.app_room_livedata_viewmodel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_room_livedata_viewmodel.adapter.StudentAdapter;
import com.example.app_room_livedata_viewmodel.database.MyDatabase;
import com.example.app_room_livedata_viewmodel.entity.Student;
import com.example.app_room_livedata_viewmodel.viewmodel.StudentViewModel;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
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

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudents().observe(this, (students) -> {
            // 當 student 資料表紀錄有變更時要做的事
            this.students.clear();
            this.students.addAll(students);
            studentAdapter.notifyDataSetChanged();
        });

        // Add button
        findViewById(R.id.btnAdd).setOnClickListener(view -> {
            /*
            Student student = new Student(faker.name().firstName(), new Random().nextInt(20) + 20);
            new AccessRoomTask(student).execute("add");
            */
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            // Layout
            View dialogLayout = layoutInflater.inflate(R.layout.dialog_layout_student, null);
            // View items
            EditText etName = dialogLayout.findViewById(R.id.etName);
            EditText etAge = dialogLayout.findViewById(R.id.etAge);
            etName.setText(faker.name().firstName());
            etAge.setText("" + (new Random().nextInt(20) + 20));
            // AlertDialog
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Add Student");
            dialog.setView(dialogLayout);
            dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Student student = new Student();
                    student.name = etName.getText().toString();
                    student.age = Integer.valueOf(etAge.getText().toString());
                    new AccessRoomTask(student).execute("add");
                }
            });
            dialog.setNegativeButton("Cancel", null);
            dialog.create().show();

        });

        // Update
        listStudent.setOnItemClickListener((adapterView, view, position, id) -> {
            Student student = students.get(position);
            student.name = faker.name().firstName();
            student.age = new Random().nextInt(20) + 20;
            new AccessRoomTask(student).execute("update");
        });

        // Delete
        listStudent.setOnItemLongClickListener((adapterView, view, position, id) -> {
            Student student = students.get(position);
            new AccessRoomTask(student).execute("delete");
            return true;
        });

    }

    // Add/Update/Delete Thread Task
    private class AccessRoomTask extends AsyncTask<String, Void, Void> {
        private Student student;

        AccessRoomTask(Student student) {
            this.student = student;
        }

        @Override
        protected Void doInBackground(String... mode) {
            switch (mode[0]) {
                case "add":
                    studentViewModel.getMyDatabase().studentDao().insert(student);
                    break;
                case "update":
                    studentViewModel.getMyDatabase().studentDao().update(student);
                    break;
                case "delete":
                    studentViewModel.getMyDatabase().studentDao().delete(student);
                    break;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context, "Room Access OK !", Toast.LENGTH_SHORT).show();
        }
    }

}