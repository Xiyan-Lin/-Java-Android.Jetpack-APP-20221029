package com.example.app_paging_boundry.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofirClient {
    private static final String BASE_URL = "https://api.github.com/";
    private static RetrofirClient retrofirClient;
    private Retrofit retrofit;

    private RetrofirClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofirClient getInstance() {
        if(retrofirClient == null) {
            retrofirClient = new RetrofirClient();
        }
        return retrofirClient;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
