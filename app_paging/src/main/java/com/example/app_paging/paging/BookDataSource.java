package com.example.app_paging.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.app_paging.api.RetrofitClient;
import com.example.app_paging.model.Book;
import com.example.app_paging.model.BookApiResponse;
import com.example.app_paging.model.Query;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 當頁 Book 的資料集
public class BookDataSource extends PageKeyedDataSource<Integer, Book> {
    public static final int FIRST_PAGE = 1;

    // 載入 Book 的資料集要做的事
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Book> callback) {
        RetrofitClient.getApiClient().getApiInterface().getBooks(Query.getSearchQueryStr(), FIRST_PAGE)
                .enqueue(new Callback<BookApiResponse>() {
                    @Override
                    public void onResponse(Call<BookApiResponse> call, Response<BookApiResponse> response) {
                        if(response.body() != null) {
                            callback.onResult(response.body().getBooks(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<BookApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {
        // nothing
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Book> callback) {

    }
}
