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
    //MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_watching,container,false);
        CameraActivity activity= (CameraActivity) getActivity();
        if (activity.hasPermission()) {
            activity.setFragment();
            activity.InitSpeaker();
        } else {
            activity.requestPermission();
        }
        return view;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        CameraActivity activity= (CameraActivity) getActivity();
        if (activity.hasPermission()) {
            activity.setFragment();
            activity.InitSpeaker();
        } else {
            activity.requestPermission();
        }
    }

    public WatchingHolder(){

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        CameraActivity activity= (CameraActivity) getActivity();
        activity.ShutDownSpeaker();
    }
}