package com.example.parus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class HearingHolder extends Fragment {
    TextView speech_text;
    CardView record_button;
    TextView record_text;
    boolean recording_status;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_hearing,container,false);
        this.speech_text=view.findViewById(R.id.text_dashboard);
        this.record_button=view.findViewById(R.id.record_button);
        this.record_text=view.findViewById(R.id.record_text);
        this.recording_status=false;
        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!recording_status){
                    record_text.setText(R.string.record_status);
                    recording_status=true;
                    getSpeechForm(view,getContext());
                }else{
                    recording_status=false;
                    record_text.setText(R.string.wait_status);
                }
            }
        });
        return view;
    }

    public void getSpeechForm(View view,Context context){
       // Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        Intent intent=new Intent(context,DetectorActivity.class);
        intent.putExtra(CameraActivity.RECOGNITION_TYPE,
                CameraActivity.VOICE_RECOGNITION);
        context.startActivity(intent);
    }

}
