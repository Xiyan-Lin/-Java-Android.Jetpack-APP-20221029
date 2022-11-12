package com.example.app_room_dice.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 資料表中每一筆紀錄的欄位結構
@Entity(tableName = "dice")
public class Dice {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "id") // 若資料表欄位名稱與物件屬性名稱一樣 可以不用設定
    private Integer d1;

    private Integer d2;

    private Integer d3;

    private Integer sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getD1() {
        return d1;
    }

    public void setD1(Integer d1) {
        this.d1 = d1;
    }

    public Integer getD2() {
        return d2;
    }

    public void setD2(Integer d2) {
        this.d2 = d2;
    }

    public Integer getD3() {
        return d3;
    }

    public void setD3(Integer d3) {
        this.d3 = d3;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "id=" + id +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", d3=" + d3 +
                ", sum=" + sum +
                '}';
    }
}
