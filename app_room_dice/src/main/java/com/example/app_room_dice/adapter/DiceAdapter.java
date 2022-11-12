package com.example.app_room_dice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_room_dice.R;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // Layout 配置
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        // 將 R.layout.list_item 物件化
        convertView = layoutInflater.inflate(R.layout.list_item, null);
        TextView tvId = convertView.findViewById(R.id.tv_id);
        ImageView tvD1 = convertView.findViewById(R.id.tv_d1);
        ImageView tvD2 = convertView.findViewById(R.id.tv_d2);
        ImageView tvD3 = convertView.findViewById(R.id.tv_d3);
        TextView tvSum = convertView.findViewById(R.id.tv_sum);
        // 將資料逐筆配置到 UI 上
        Dice dice = diceList.get(i);
        tvId.setText(dice.getId() + "");
        setDiceImage(tvD1, dice.getD1());
        setDiceImage(tvD2, dice.getD2());
        setDiceImage(tvD3, dice.getD3());
        tvSum.setText(dice.getSum() + "");
        return convertView;
    }

    private void setDiceImage(ImageView imageView, int number) {
        int[] diceImages = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
                            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
        imageView.setImageResource(diceImages[number-1]);
    }
}
