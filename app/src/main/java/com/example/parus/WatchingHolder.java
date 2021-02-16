package com.example.parus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class WatchingHolder extends Fragment {
    CameraActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_watching,container,false);
        if (activity.hasPermission()) {
        //    activity.setFragment();
        } else {
            activity.requestPermission();
        }
        return view;
    }

    public WatchingHolder(CameraActivity activity){
        this.activity=activity;
    }
}