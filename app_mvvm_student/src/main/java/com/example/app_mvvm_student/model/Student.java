package com.example.app_mvvm_student.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String name;

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    public int age;

    @ColumnInfo(name = "imageBase64", typeAffinity = ColumnInfo.TEXT)
    public String imageBase64;

    // 給 room 進行 O/R Mapping 構造使用
    public Student(int id, String name, int age, String imageBase64) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.imageBase64 = imageBase64;
    }

    @Ignore
    public Student(String name, int age, String imageBase64) {
        this.name = name;
        this.age = age;
        this.imageBase64 = imageBase64;
    }


}
