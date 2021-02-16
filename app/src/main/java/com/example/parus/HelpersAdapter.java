package com.example.parus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelpersAdapter extends FragmentStatePagerAdapter {
    Context context;
    final String TAG="Library_Adapter";
    final static int WATCH_TYPE=0;
    final static int HEAR_TYPE=1;
    final static int PRON_TYPE=2;
    CameraActivity activity;
    ArrayList<Fragment> holders;

    public HelpersAdapter(@NonNull FragmentManager fm,@NonNull Context context, CameraActivity activity) {
        super(fm);
        this.context=context;
        this.activity=activity;
        holders=new ArrayList<Fragment>();
        holders.add(new WatchingHolder(activity));
        holders.add(new HearingHolder());
        holders.add(new PronouncingHolder());
    }

    /*@Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return WATCH_TYPE;
            case 1:
                return HEAR_TYPE;
            case 2:
                return PRON_TYPE;
            default:
                return WATCH_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder;
        switch (viewType){
            case 0:
               viewHolder = (LayoutInflater.from(parent.getContext()))
                        .inflate(R.layout.fragment_watching,
                                parent,false);
                Log.v(TAG,"I ghave added Viewpager, dude!");
                holders.add(0,new WatchingHolder(viewHolder));
                return holders.get(0);
            case 1:
                viewHolder= (LayoutInflater.from(parent.getContext()))
                        .inflate(R.layout.fragment_hearing,
                                parent,false);
                Log.v(TAG,"I ghave added Viewpager, dude!");
                holders.add(1,new HearingHolder(viewHolder));
                return holders.get(1);
            case 2:
                viewHolder= (LayoutInflater.from(parent.getContext()))
                        .inflate(R.layout.fragment_pronouncing,
                                parent,false);
                Log.v(TAG,"I ghave added Viewpager, dude!");
                holders.add(2,new PronouncingHolder(viewHolder));
                return holders.get(2);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (position){
            case 0:
                 WatchingHolder watchingHolder=(WatchingHolder) holder;
                 watchingHolder.onBindModel();
                break;
            case 1:
                HearingHolder hearingHolder=(HearingHolder) holder;
                hearingHolder.onBindModel(context);
                break;
            case 2:
                PronouncingHolder pronouncingHolder = (PronouncingHolder) holder;
                pronouncingHolder.onBindModel();
                break;
        }
    } */



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return holders.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
