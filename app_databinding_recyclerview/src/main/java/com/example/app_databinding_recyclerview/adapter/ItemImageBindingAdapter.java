package com.example.app_databinding_recyclerview.adapter;

import android.graphics.Color;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.app_databinding_recyclerview.R;
import com.squareup.picasso.Picasso;

public class ItemImageBindingAdapter {
    // 用來加載 app:itemImage="@{book.imagePath}"
    @BindingAdapter("itemImage")
    public static void setImage(ImageView imageView, String imagePath) {
        if(TextUtils.isEmpty(imagePath)) {
           imageView.setBackgroundColor(Color.GRAY);
           return;
        }
        Picasso.get()
                .load(imagePath)
                .placeholder(R.drawable.ic_launcher_background) // 圖片在載入過程中要顯示的圖片
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);
    }
}
