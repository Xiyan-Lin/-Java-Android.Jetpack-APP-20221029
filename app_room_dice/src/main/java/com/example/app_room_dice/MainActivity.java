package com.example.app_room_dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app_room_dice.dao.DiceDao;
import com.example.app_room_dice.room.DiceDatabase;

public class MainActivity extends AppCompatActivity {
    private DiceDao diceDao;
    private Context context;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this; // 很重要! 容易忘記寫~
        tv = findViewById(R.id.tv);
        diceDao = DiceDatabase.getInstance(context).diceDao();
    }
}