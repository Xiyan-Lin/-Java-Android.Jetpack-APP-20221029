package com.example.app_layout_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener((view) -> {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        });
    }
}