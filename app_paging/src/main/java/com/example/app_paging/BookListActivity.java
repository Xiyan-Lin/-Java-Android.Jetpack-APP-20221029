package com.example.app_paging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.app_paging.model.Book;
import com.example.app_paging.model.Query;
import com.example.app_paging.paging.BookListViewModel;
import com.example.app_paging.paging.BookPagedListAdapter;

import static com.example.app_paging.MainActivity.SEARCH_TAG;

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

        // 取得 MainActivity 傳來的參數
        String queryText = null;
        Intent intent = getIntent();
        if(intent.hasExtra(SEARCH_TAG)) {
            // 取得傳來的參數
            queryText = intent.getStringExtra(SEARCH_TAG);
            Toast.makeText(context, queryText, Toast.LENGTH_SHORT).show();
        }

        // 配置 recyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);

        // 適配器
        BookPagedListAdapter adapter = new BookPagedListAdapter(context);
        recyclerView.setAdapter(adapter);

        // ViewModel
        bookListViewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        bookListViewModel.setSearchQuery(queryText);
        bookListViewModel.getBookPagedList().observe(this, books -> adapter.submitList(books));

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