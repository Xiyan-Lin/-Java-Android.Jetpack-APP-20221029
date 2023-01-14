package com.example.app_paging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app_paging.model.Query;
import com.example.app_paging.paging.BookListViewModel;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookListViewModel bookListViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        context = this;

        // Title 中顯示 <- 返回箭頭
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // 取得 MainActivity 傳來的
        Intent intent = getIntent();
        if(intent.hasExtra("SEARCH TAG")) {
            // 取得傳來的參數
            String queryText = intent.getStringExtra("SEARCH TAG");
            // 存放參數
            Query.setSearchQueryStr(queryText);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}