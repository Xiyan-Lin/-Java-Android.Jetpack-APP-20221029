package com.example.app_mvvm_student.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.app_mvvm_student.api.Api;
import com.example.app_mvvm_student.database.StudentDao;
import com.example.app_mvvm_student.model.Student;

import java.nio.channels.AsynchronousByteChannel;
import java.util.List;

public class StudentRepository {
    private StudentDao studentDao;
    private Api api;

    public StudentRepository(StudentDao studentDao, Api api) {
        this.studentDao = studentDao;
        this.api = api;
    }

    public LiveData<Student> getStudent(int id) {
        return studentDao.getById(id);
    }

    public LiveData<List<Student>> queryStudents() {
        return studentDao.queryAll();
    }

    public void addStudent(Student student) {
        // 1. 透過 Api 遠端 insert
        // 2. insert 成功之後再將 student insert 到 room 資料表中
        AsyncTask.execute(() -> {studentDao.insert(student);});
    }

    // 使用者下拉重整畫面時
    public void refresh() {
        // 1. 透過 Api 遠端抓取最新資料
        // 2. 將最新資料複製給 room
        queryStudents();
    }


}
