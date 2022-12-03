package com.example.app_viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editHeight, editWeight;
    private TextView textResult;
    private Button button;
    private ListView listView;
    private MyViewModel myViewModel;
    private BmiAdapter bmiAdapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 全螢幕
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        context = this;

        setTitle("BMI 計算紀錄器");
        // View UI
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);
        textResult = findViewById(R.id.text_result);
        button = findViewById(R.id.button);

        // Adapter 適配器
        bmiAdapter = new BmiAdapter(context);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(bmiAdapter); // 設定 listview 的適配器

        // Event
        button.setOnClickListener((view -> {
            double h = Double.parseDouble(editHeight.getText().toString());
            double w = Double.parseDouble(editWeight.getText().toString());
            Bmi bmi = new Bmi(h, w);
            myViewModel.addBmi(bmi);
            dataBinding();
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        dataBinding();
    }

    // data binding: 資料配置
    private void dataBinding() {
        List<Bmi> bmiList = myViewModel.getBmiList();
        if(bmiList.size() <= 0) return;
        // 將最新資料配置在 UI 上
        Bmi lastBmi = bmiList.get(bmiList.size() - 1);
        editHeight.setText(String.format("%.1f", lastBmi.getHeight()));
        editWeight.setText(String.format("%.1f", lastBmi.getWeight()));
        textResult.setText(String.format("%.2f", lastBmi.getBmi()));
        // 放入歷史紀錄
        bmiAdapter.setBmiList(bmiList); // 在適配器中配置新的 bmiList
        bmiAdapter.notifyDataSetChanged(); // 通知變更
    }


}