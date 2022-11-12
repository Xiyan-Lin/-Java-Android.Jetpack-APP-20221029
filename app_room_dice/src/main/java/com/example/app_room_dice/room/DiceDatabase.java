package com.example.app_room_dice.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.app_room_dice.dao.DiceDao;
import com.example.app_room_dice.entity.Dice;

@Database(entities = {Dice.class}, version = 1)
public abstract class DiceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "dice_db"; // 資料庫存檔檔名
    private static DiceDatabase diceDatabase;
    public static synchronized DiceDatabase getInstance(Context context) {
        if(diceDatabase == null) {
            diceDatabase = Room
                    .databaseBuilder(context, DiceDatabase.class, DATABASE_NAME)
                    .build();
        }
        return diceDatabase;
    }

    public abstract DiceDao diceDao();

}
