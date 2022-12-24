package com.example.app_mvvm_student.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_mvvm_student.api.Api;
import com.example.app_mvvm_student.api.RetrofitClient;
import com.example.app_mvvm_student.database.MyDatabase;
import com.example.app_mvvm_student.database.StudentDao;
import com.example.app_mvvm_student.model.Student;
import com.example.app_mvvm_student.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudents;
    private StudentRepository studentRepository;
    private RecyclerView.Adapter adapter;


    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getInstance(application);
        StudentDao studentDao = myDatabase.studentDao();
        Api api = RetrofitClient.getInstance().getApi();
        studentRepository = new StudentRepository(studentDao, api);
        liveDataStudents = studentRepository.queryStudents();
    }

    public void refresh() {
        studentRepository.refresh();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void createStudent(Student student) {
        studentRepository.addStudent(student);
    }

    public LiveData<List<Student>> queryLiveDataStudents() {
        return liveDataStudents;
    }
}
