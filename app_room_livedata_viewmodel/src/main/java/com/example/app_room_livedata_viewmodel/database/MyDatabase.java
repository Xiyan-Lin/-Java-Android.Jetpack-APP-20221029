package com.example.app_room_livedata_viewmodel.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.app_room_livedata_viewmodel.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "my_db";
    private static MyDatabase databaseInstance;
    public static synchronized MyDatabase getInstance(Context context) {
        if(databaseInstance == null) {
                databaseInstance = Room
                        .databaseBuilder(context.getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                        //.allowMainThreadQueries() // 允許在主執行緒(UI Thread)上操作資料庫
                        .build();
        }
        return databaseInstance;
    }

    public abstract StudentDao studentDao();
}
