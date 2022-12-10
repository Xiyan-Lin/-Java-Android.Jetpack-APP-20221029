package com.example.app_databinding;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class EventHandleListener {
    private Context context;

    public EventHandleListener(Context context) {
        this.context = context;
    }

    public void onButtonClicked(View view) {
        Toast.makeText(context, "Click me!", Toast.LENGTH_SHORT).show();
    }

    public void onButtonClicked2(View view) {
        Toast.makeText(context, "Click me 2!", Toast.LENGTH_SHORT).show();
    }
}
