package com.example.app_databinding_recyclerview_room.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.app_databinding_recyclerview_room.R;

public class ItemImageBindingAdapter {
    // 用來加載 app:itemImage="@{student.id}"
    @BindingAdapter("itemImage")
    public static void setImage(ImageView imageView, int id) {
        Context context = imageView.getContext();
        int resId = context.getResources().getIdentifier("s" + (id%10), "drawable", context.getPackageName());
        imageView.setImageResource(resId);

    }
}
