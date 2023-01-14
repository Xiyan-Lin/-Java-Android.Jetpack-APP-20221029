package com.example.app_paging_boundry.paging;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.app_paging_boundry.db.UserDatabase;
import com.example.app_paging_boundry.model.User;

public class UserViewModel extends AndroidViewModel {
    public LiveData<PagedList<User>> userPagedList;
    private UserDatabase database;
    public UserViewModel(@NonNull Application application) {
        super(application);
        database = UserDatabase.getInstance(application);
        int pageSize = UserBoundaryCallback.PER_PAGE;
        userPagedList = new LivePagedListBuilder<>(database.userDao().getUserList(), pageSize)
                .setBoundaryCallback(new UserBoundaryCallback(application))
                .build();
    }

    // 刷新數據
    public void refresh() {
        AsyncTask.execute(() -> {
            // 清除資料表內容
            database.userDao().clear();
        });
    }
}
