package com.example.app_event_bus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 啟用 EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停用 EventBus
        EventBus.getDefault().unregister(this);
    }

    public void btnOnClick(View view) {
        EventBus.getDefault().post(new MessageEvent("要發送的訊息"));
    }

    // 註冊 Subscribe, 觀察的目標是 MessageEvent 物件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        // 將要做的事寫在這裡
        System.out.println(event.getMessage());
    }

}