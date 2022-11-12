package com.example.app_room_dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app_room_dice.adapter.DiceAdapter;
import com.example.app_room_dice.dao.DiceDao;
import com.example.app_room_dice.entity.Dice;
import com.example.app_room_dice.room.DiceDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private DiceDao diceDao;
    private Context context;
    private ListView listView;
    private DiceAdapter diceAdapter;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Init
        context = this; // 很重要! 容易忘記寫~
        listView = findViewById(R.id.list_view);
        fab = findViewById(R.id.fab);
        diceDao = DiceDatabase.getInstance(context).diceDao();

        // adapter 適配器
        diceAdapter = new DiceAdapter(context);
        listView.setAdapter(diceAdapter); // 設定 listview 的適配器

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
            // 反排序
            Collections.sort(diceList, (o1, o2) -> {
                return (int)(o2.getId() - o1.getId());
            });
            // 透過 UI (主)執行緒來配置/變更畫面內容
            runOnUiThread(() -> {
                diceAdapter.setDiceList(diceList); // 在適配器中配置新的 diceList
                diceAdapter.notifyDataSetChanged(); // 通知適配器資料已經變更
                setTitle(diceList.size() + "筆");
            });
        });
    }

}