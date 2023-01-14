package com.example.app_paging.api;

import com.example.app_paging.model.BookApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("search/{query}/{page}")
    Call<BookApiResponse> getBooks(
        @Path("query") String query, @Path("page") int page
    );
}
