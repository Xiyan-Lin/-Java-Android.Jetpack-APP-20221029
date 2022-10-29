package com.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// 主程式
public class MainActivity extends AppCompatActivity {

    // 程式進入點
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 配置 UI 畫面
        setContentView(R.layout.activity_main);
    }
}