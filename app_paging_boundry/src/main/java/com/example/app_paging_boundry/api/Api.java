package com.example.app_paging_boundry.api;

import com.example.app_paging_boundry.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    // https://api.github.com/users?since=0&per_page=5
    @GET("users")
    Call<List<User>> getUsers(
            @Query("since") int since, @Query("per_page") int perPage
    );
}
