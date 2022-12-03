package com.example.app_viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BmiAdapter extends BaseAdapter {
    private List<Bmi> bmiList;
    private Context context;

    public BmiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bmiList == null ? 0: bmiList.size();
    }

    @Override
    public Object getItem(int i) {
        return bmiList == null ? null : bmiList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return bmiList == null ? 0 : i;
    }

    public void setBmiList(List<Bmi> bmiList) {
        this.bmiList = bmiList;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        // Layout 配置
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            // 將 ui 結構全部放到 holder 物件中
            holder.tvHeight = convertView.findViewById(R.id.tv_height);
            holder.tvWeight = convertView.findViewById(R.id.tv_weight);
            holder.tvResult = convertView.findViewById(R.id.tv_result);
            // 放到 convertView 中保存
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 將資料逐筆配置到 UI 上
        Bmi bmi = bmiList.get(i);
        holder.tvHeight.setText(bmi.getHeight() + "");
        holder.tvWeight.setText(bmi.getWeight() + "");
        holder.tvResult.setText(bmi.getBmi() + "");

        return convertView;
    }

    // 優化 ListView 避免重新加載, 提高呈現效能
    static class ViewHolder {
        TextView tvHeight;
        TextView tvWeight;
        TextView tvResult;
    }

}
