package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.text_view);

        button.setOnClickListener((view) -> {
            int lotto = random.nextInt(100);
            textView.setText(lotto + "");
        });
    }

    // 若 Bundle 物件有存放資料, 則將資料取出
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.getString("lotto") != null) {
            textView.setText(savedInstanceState.getString("lotto"));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    // 將 lotto 資料放入 Bundle 物件中
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("lotto", textView.getText().toString());
        super.onSaveInstanceState(outState);
    }
}