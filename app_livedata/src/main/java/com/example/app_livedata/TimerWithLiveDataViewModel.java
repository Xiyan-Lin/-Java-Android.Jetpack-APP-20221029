package com.example.app_livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class TimerWithLiveDataViewModel extends ViewModel {

    private Timer timer;
    private MutableLiveData<Integer> currentSecond;

    public LiveData<Integer> getCurrentSecond() {
        if(currentSecond == null) {
            currentSecond = new MutableLiveData<>();
        }
        return currentSecond;
    }

    public void startTiming() {
        if(timer == null) {
            currentSecond.setValue(0);
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    currentSecond.postValue(currentSecond.getValue() + 1);
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        timer.cancel();
    }
}
