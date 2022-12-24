package com.example.app_mvvm_student.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.app_mvvm_student.api.Api;
import com.example.app_mvvm_student.database.StudentDao;
import com.example.app_mvvm_student.model.Student;

import java.nio.channels.AsynchronousByteChannel;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        api.addStudent(student).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // 2. insert 成功之後再將 student insert 到 room 資料表中
                AsyncTask.execute(() -> {studentDao.insert(response.body());});
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                Log.i("MyLog", "新增失敗: " + this.getClass().getSimpleName());
            }
        });

    }

    // 使用者下拉重整畫面時
    public void refresh() {
        // 1. 透過 Api 遠端抓取最新資料
        api.queryStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> students = response.body();
                AsyncTask.execute(() -> {
                    // 2. 將最新資料複製給 room
                    for(Student student: students) {
                        studentDao.insert(student);
                    }
                    // 3. 刪除遠端有但是本地端沒有的資料(同步)
                    int[] ids = students.stream().mapToInt(s -> s.id).toArray(); // 遠端已有的 id
                    studentDao.deleteNotInId(ids);
                });
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.i("MyLog", "查詢失敗: " + this.getClass().getSimpleName());
            }
        });

    }


}
