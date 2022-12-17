package com.example.app_databinding_recyclerview_room.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_databinding_recyclerview_room.database.MyDatabase;
import com.example.app_databinding_recyclerview_room.model.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudents;
    private RecyclerView.Adapter adapter;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getInstance(application);
        liveDataStudents = myDatabase.studentDao().queryAll();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public LiveData<List<Student>> getLiveDataStudents() {
        return liveDataStudents;
    }

    // 若要使用此方法則需要另外實作 AsyncTask
    public int getStudentCount() {
        return myDatabase.studentDao().getCount();
    }

    public void addStudent(Student student) {
        new StudentTask().execute(student, "add");
    }

    class StudentTask extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... objs) {
            Student student = (Student)objs[0];
            String mode = objs[1] + "";
            switch (mode) {
                case "add":
                    myDatabase.studentDao().add(student);
                    break;
                case "update":
                    break;
                case "delete":
                    break;
            }
            return null;
        }
    }

}
