package com.example.app_room_dice.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app_room_dice.entity.Dice;

import java.util.List;

@Dao
public interface DiceDao {

    @Insert
    public long insert(Dice dice);

    @Query("select id, d1, d2, d3, sum from dice")
    public List<Dice> queryAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void update(Dice dice);

    @Delete
    public void delete(Dice dice);

}
