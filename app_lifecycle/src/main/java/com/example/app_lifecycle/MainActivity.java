package com.example.app_lifecycle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private TextView tv;
    private int i = 0;
    private boolean play = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv); // 會得到 TextView 物件
        Log.i(TAG, "onCreate()");

        Runnable runnable = () -> {
            while (true) {
                try {
                    if(play) {
                        changeContent();
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
        };
        new Thread(runnable).start();

        // 按下 tv 事件
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 另外啟動一個新的 Activity
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });

    }

    private void changeContent() {
        // 將要變更 UI 的任務給 UI Thread 去執行
        runOnUiThread(() -> { // 放入到 Queue 中排程
            tv.setText(String.valueOf(++i)); // 變更 UI
        });
        Log.i(TAG, String.valueOf(i));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
        // 對話視窗設定/建構
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否要繼續?");
        builder.setPositiveButton("繼續", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                play = true;
            }
        });
        builder.setNegativeButton("離開", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish(); // 將 Activity 關閉
            }
        });
        // 建立對話視窗
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        play = false;
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }
}