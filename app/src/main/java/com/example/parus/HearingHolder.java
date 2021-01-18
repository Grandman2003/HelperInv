package com.example.parus;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class HearingHolder extends RecyclerView.ViewHolder {
    TextView speech_text;
    CardView record_button;
    TextView record_text;
    View itemview;
    boolean recording_status;

    public HearingHolder(@NonNull View itemView) {
        super(itemView);
        this.itemview=itemView;
        this.speech_text=itemview.findViewById(R.id.text_dashboard);
        this.record_button=itemview.findViewById(R.id.record_button);
        this.record_text=itemview.findViewById(R.id.record_text);
        this.recording_status=false;
    }
    public void onBindModel(Context context){
        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!recording_status){
                    record_text.setText(R.string.record_status);
                    recording_status=true;
                    getSpeechForm(itemview,context);
                }else{
                    recording_status=false;
                    record_text.setText(R.string.wait_status);
                }
            }
        });
    }
    public void getSpeechForm(View view,Context context){
       // Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra(MainActivity.RECOGNITION_TYPE,
                MainActivity.VOICE_RECOGNITION);
        context.startActivity(intent);
    }

}
