package com.example.app_viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editHeight, editWeight;
    private TextView textResult, textRecord;
    private Button button;
    private MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);
        textResult = findViewById(R.id.text_result);
        textRecord = findViewById(R.id.text_record);
        button = findViewById(R.id.button);

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

    // data binding
    private void dataBinding() {
        List<Bmi> bmiList = myViewModel.getBmiList();
        if(bmiList.size() <= 0) return;
        // 將最新資料配置在 UI 上
        Bmi lastBmi = bmiList.get(bmiList.size() - 1);
        editHeight.setText(String.format("%.1f", lastBmi.getHeight()));
        editWeight.setText(String.format("%.1f", lastBmi.getWeight()));
        textResult.setText(String.format("%.2f", lastBmi.getBmi()));
        // 放入歷史紀錄
        textRecord.setText(""); // 清空
        bmiList.forEach(bmi -> textRecord.append(bmi.toString()));
    }


}