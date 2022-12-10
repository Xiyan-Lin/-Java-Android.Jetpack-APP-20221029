package com.example.app_room_livedata_viewmodel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.app_room_livedata_viewmodel.database.MyDatabase;
import com.example.app_room_livedata_viewmodel.entity.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private MyDatabase myDatabase;
    private LiveData<List<Student>> liveDataStudents;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        myDatabase = MyDatabase.getInstance(application);
        liveDataStudents = myDatabase.studentDao().queryAll();
    }

    public LiveData<List<Student>> getLiveDataStudents() {
        return liveDataStudents;
    }

    public MyDatabase getMyDatabase() {
        return myDatabase;
    }
}
