package com.example.app_paging_boundry.paging;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.example.app_paging_boundry.api.RetrofitClient;
import com.example.app_paging_boundry.db.UserDatabase;
import com.example.app_paging_boundry.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserBoundaryCallback extends PagedList.BoundaryCallback<User> {
    public static final int PER_PAGE = 5;
    private Application application;
    public UserBoundaryCallback(Application application) {
        this.application = application;
    }

    // 時機：若資料庫沒有數據時
    // onZeroItemsLoaded 沒有 Item 有數據時呼叫這個方法，通常是第一次進入列表時調用。
    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        getFirstPage();
    }

    // 時機：當用戶滑動到 RecyclerView 底部
    // onItemAtEndLoaded 在讀到最後一個有數據的 Item 時呼叫，
    // 這時候會去跟 DataSource 要新的資料塞進接下來的 Item 裡。
    @Override
    public void onItemAtEndLoaded(@NonNull User itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        getNextPage(itemAtEnd);
    }

    // 當資料表沒有數據時要透過 retrofit 加載數據
    private void getFirstPage() {
        int since = 0;
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(since, PER_PAGE)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if(response.body() != null) {
                            List<User> users = response.body(); // 網路爬來的資料
                            addUsers(users); // 插入數據
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });
    }

    // 根據 endUser (最末筆 user) 來加載下一頁數據
    private void getNextPage(User endUser) {
        int since = endUser.getId();
        RetrofitClient.getInstance()
                .getApi()
                .getUsers(since, PER_PAGE)
                .enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if(response.body() != null) {
                            List<User> users = response.body(); // 網路爬來的資料
                            addUsers(users); // 插入數據
                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {

                    }
                });
    }

    // 插入數據
    private void addUsers(List<User> users) {
        AsyncTask.execute(() -> {
            UserDatabase.getInstance(application).userDao().insertUsers(users);
        });
    }
}
