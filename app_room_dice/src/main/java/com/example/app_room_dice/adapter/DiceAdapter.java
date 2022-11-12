package com.example.app_room_dice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.app_room_dice.entity.Dice;

import java.util.List;

// 適配器 Adapter
// 資料庫的每一筆紀錄要如何配置到指定的 UI 上面
public class DiceAdapter extends BaseAdapter {
    private List<Dice> diceList;
    private Context context;

    public DiceAdapter(Context context) { // 取得環境資訊
        this.context = context;
    }

    public void setDiceList(List<Dice> diceList) { // 取得最新 diceList 資料
        this.diceList = diceList;
    }


    @Override
    public int getCount() {
        return diceList == null ? 0 : diceList.size();
    }

    @Override
    public Object getItem(int i) {
        return diceList == null ? null : diceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return diceList == null ? 0 : diceList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
