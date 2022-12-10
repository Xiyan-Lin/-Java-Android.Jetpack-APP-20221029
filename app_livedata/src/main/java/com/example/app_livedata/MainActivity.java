package com.example.app_livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = findViewById(R.id.tvTime);
        initComponent();
    }

    private void initComponent() {
        // 得到 ViewModel
        TimerWithLiveDataViewModel timerWithLiveDataViewModel =
                ViewModelProviders.of(this).get(TimerWithLiveDataViewModel.class);
        // 得到 LiveData
        MutableLiveData<Integer> liveData =
                (MutableLiveData<Integer>)timerWithLiveDataViewModel.getCurrentSecond();
        // 觀察數據變化
        liveData.observe(this, (second) -> {
            tvTime.setText(second + "");
        });
        // 開始計時
        timerWithLiveDataViewModel.startTiming();
    }

}