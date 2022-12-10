package com.example.app_livedata_fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareDataViewModel extends ViewModel {
    private MutableLiveData<Integer> progress;

    public LiveData<Integer> getProgress() {
        if(progress == null) {
            progress = new MutableLiveData<>();
        }
        return progress;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        progress = null;
    }
}
