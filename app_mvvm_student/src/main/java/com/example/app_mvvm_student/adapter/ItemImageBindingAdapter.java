package com.example.app_mvvm_student.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import java.util.Base64;

public class ItemImageBindingAdapter {
    // 用來加載 app:itemImage="@{student.imageBase64}"
    @BindingAdapter("itemImage")
    public static void setImage(ImageView imageView, String imageBase64) {
        Context context = imageView.getContext();
        byte[] decodedString = Base64.getDecoder().decode(imageBase64);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(bitmap);
    }
}
