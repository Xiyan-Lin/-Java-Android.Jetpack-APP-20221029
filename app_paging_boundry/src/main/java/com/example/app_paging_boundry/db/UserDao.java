package com.example.app_paging_boundry.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.app_paging_boundry.model.User;

import java.util.List;

@Dao
public interface UserDao {

    // 清空數據
    @Query("DELETE FROm user")
    void clear();

    // Room 對 Paging 提供了原生支援，可以直接返回 DataSource.Factory
    // 便於 LivePagedListBuilder 在創建時使用
    @Query("SELECT * FROM user")
    DataSource.Factory<Integer, User> getUserList();

    // 一般查詢返回
    @Query("SELECT * FROM user")
    List<User> getUsers();

}
