package com.example.app_paging;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // 在 Title 中顯示 icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.blue_circle_48);

        EditText searchEdit = findViewById(R.id.books_edt);
        Button searchBtn = findViewById(R.id.books_btn);

        searchBtn.setOnClickListener(view -> {
            String searchStr = searchEdit.getText().toString();
            if(searchStr.isEmpty()) {
                Toast.makeText(context, "Search box is empty!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(context, BookListActivity.class);
                intent.putExtra("SEARCH TAG", searchStr);
                startActivity(intent);
            }
        });
    }
}