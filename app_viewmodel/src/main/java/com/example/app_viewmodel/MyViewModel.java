package com.example.app_viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

// ViewModel vs AndroidViewModel(會自行帶入 Application 變數)
//public class MyViewModel extends ViewModel {
public class MyViewModel extends AndroidViewModel {

    private List<Bmi> bmiList = new ArrayList<>();
    private Context context;

    public MyViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public List<Bmi> getBmiList() {
        return bmiList;
    }

    public void addBmi(Bmi bmi) {
        bmiList.add(bmi);
    }
}
