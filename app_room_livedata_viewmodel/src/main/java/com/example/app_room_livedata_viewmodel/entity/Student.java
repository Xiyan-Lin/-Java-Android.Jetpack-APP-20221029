package com.example.app_room_livedata_viewmodel.entity;

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

    @Ignore
    public String temp;

    // Room 預設會持久化此建構數據
    // 資料表得到數據後會使用此建構子建構物件
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Room 不會持久化此建構數據
    // Room 會忽略使用此建構子來建構物件資料
    @Ignore
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
