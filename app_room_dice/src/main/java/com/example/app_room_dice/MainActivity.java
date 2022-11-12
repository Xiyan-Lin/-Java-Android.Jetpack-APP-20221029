package com.example.app_room_dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_room_dice.dao.DiceDao;
import com.example.app_room_dice.entity.Dice;
import com.example.app_room_dice.room.DiceDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private DiceDao diceDao;
    private Context context;
    private TextView tv;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init
        context = this; // 很重要! 容易忘記寫~
        tv = findViewById(R.id.tv);
        fab = findViewById(R.id.fab);
        diceDao = DiceDatabase.getInstance(context).diceDao();

        // Event
        fab.setOnClickListener((view) -> {
            Random random = new Random();
            Dice dice = new Dice();
            dice.setD1(random.nextInt(6)+1);
            dice.setD2(random.nextInt(6)+1);
            dice.setD3(random.nextInt(6)+1);
            dice.setSum();
            // 存入資料表
            // 預設要透過執行緒
            Executors.newSingleThreadExecutor().execute(() -> {
                // 新增
                diceDao.insert(dice);
                // 查詢所有資料 & 顯示在 tv 上
                showDice();
            });
        });

        // 首要工作
        showDice();
    }

    // 查詢所有資料 & 顯示在 tv 上
    private void showDice() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // 查詢所有資料 & 顯示在 tv 上
            List<Dice> diceList = diceDao.queryAll();
            // 透過 UI (主)執行緒來配置/變更畫面內容
            runOnUiThread(() -> {
                tv.setText(diceList.toString());
                setTitle(diceList.size() + "筆");
            });
        });
    }

}