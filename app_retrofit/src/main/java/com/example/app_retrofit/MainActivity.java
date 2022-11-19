package com.example.app_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.app_retrofit.api.Api;
import com.example.app_retrofit.api.RetrofitClient;
import com.example.app_retrofit.model.Post;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);

        new Thread(()->{

            Api api = RetrofitClient.getInstance().getApi();
            api.getPosts().enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    Log.i("retrofit", response.body().toString());
                    runOnUiThread(() -> {
                        tv.setText(response.body().toString());
                    });
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    Log.i("retrofit", t.toString());
                }
            });

        }).start();



    }
}