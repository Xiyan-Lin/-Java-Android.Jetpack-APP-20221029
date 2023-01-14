package com.example.app_paging_boundry.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.github.com/";
    private static RetrofitClient retrofirClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if(retrofirClient == null) {
            retrofirClient = new RetrofitClient();
        }
        return retrofirClient;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
