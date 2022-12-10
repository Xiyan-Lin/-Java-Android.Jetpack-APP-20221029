package com.example.app_livedata_fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

public class OneFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_one, container, false);
        SeekBar seekBar = parentView.findViewById(R.id.seekBar);

        // 建立 ViewModel
        ShareDataViewModel shareDataViewModel = ViewModelProviders.of(getActivity()).get(ShareDataViewModel.class);
        // 建立 livedata
        MutableLiveData<Integer> progressLiveData =( MutableLiveData<Integer>)shareDataViewModel.getProgress();
        // 監聽
        progressLiveData.observe(getActivity(), (progress) -> seekBar.setProgress(progress));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("MyLog", "Fragment One SeekBar value: " + progress);
                // 變更 livedata 的資料
                progressLiveData.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return parentView;
    }
}
