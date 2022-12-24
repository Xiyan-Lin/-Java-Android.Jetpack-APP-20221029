package com.example.app_mvvm_student.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app_mvvm_student.model.Student;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    // 實現遠端與本地端的資料同步
    @Query("DELETE FROM student where id not in (:ids)")
    public void deleteNotInId(int[] ids);

    @Query("SELECT id, name, age, imageBase64 FROM student order by id desc")
    LiveData<List<Student>> queryAll();

    @Query("SELECT id, name, age, imageBase64 FROM student WHERE id = :id")
    LiveData<Student> getById(int id);

}
