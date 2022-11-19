package com.example.app_retrofit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_retrofit.adapter.PostAdapter;
import com.example.app_retrofit.api.Api;
import com.example.app_retrofit.api.RetrofitClient;
import com.example.app_retrofit.model.Post;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private PostAdapter postAdapter;
    private ListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Api api = RetrofitClient.getInstance().getApi();

        listView = findViewById(R.id.list_view);
        postAdapter = new PostAdapter(context);
        listView.setAdapter(postAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
                try {
                    api.getPost((int) id).enqueue(new Callback<Post>() {
                        @Override
                        public void onResponse(Call<Post> call, Response<Post> response) {
                            Post post = response.body();
                            // 對話視窗設定/建構
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("修改 title");
                            Log.i("retrofit", post.toString());
                            // 自訂 EditText
                            EditText et = new EditText(context);
                            et.setText(post.title);
                            builder.setView(et);
                            builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.setNegativeButton("離開", null);
                            // 建立對話視窗
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                        @Override
                        public void onFailure(Call<Post> call, Throwable t) {

                        }
                    });

                } catch (Exception e) {
                }
                return true;
            }
        });


        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            api.getPosts().enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    Log.i("retrofit", response.body().toString());
                    runOnUiThread(() -> {
                        List<Post> postList = response.body();
                        postAdapter.setPostList(postList);
                        postAdapter.notifyDataSetChanged();
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