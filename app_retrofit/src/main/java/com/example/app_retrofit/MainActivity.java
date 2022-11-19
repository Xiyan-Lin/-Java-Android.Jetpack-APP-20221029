package com.example.app_retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

        Api api = RetrofitClient.getInstance().getApi();

        TextView tv = findViewById(R.id.tv);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);



        swipeRefreshLayout.setOnRefreshListener(() -> {
            api.getPosts().enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    Log.i("retrofit", response.body().toString());
                    runOnUiThread(() -> {
                        tv.setText(response.body().toString());
                    });
                    swipeRefreshLayout.setRefreshing(false); // 將旋轉鈕關閉
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    Log.i("retrofit", t.toString());
                    swipeRefreshLayout.setRefreshing(false); // 將旋轉鈕關閉
                }
            });
        });






    }
}