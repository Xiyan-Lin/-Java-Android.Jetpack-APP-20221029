package com.example.app_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

        play = true;
        Log.i(TAG, "onRestart()");
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