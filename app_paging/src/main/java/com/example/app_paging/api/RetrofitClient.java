package com.example.app_paging.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.itbook.store/1.0/";
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getApiClient() {
        if(retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Api getApiInterface() {
        return retrofit.create(Api.class);
    }
}
