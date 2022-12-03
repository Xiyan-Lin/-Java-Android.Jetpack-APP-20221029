package com.example.app_viewmodel;

import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

// ViewModel vs AndroidViewModel(會自行帶入 Context 變數)
public class MyViewModel extends ViewModel {
    private List<Bmi> bmiList = new ArrayList<>();

    public List<Bmi> getBmiList() {
        return bmiList;
    }

    public void addBmi(Bmi bmi) {
        bmiList.add(bmi);
    }
}
