package com.example.parus;

import android.media.AudioAttributes;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class PronouncingHolder extends RecyclerView.ViewHolder {
    EditText pronouncing_text;
    SeekBar speed_bar;
    SeekBar pitch_bar;
    Button pronounce_button;
    private TextToSpeech mTTs;
    private final String TAG=".PronouncingHolder";
    View itemView;

    public PronouncingHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView=itemView;
        pronouncing_text=itemView.findViewById(R.id.pronouncing_text);
        speed_bar=itemView.findViewById(R.id.speed_bar);
        pitch_bar =itemView.findViewById(R.id.pinch_bar);
        pronounce_button=itemView.findViewById(R.id.pron_button);
        mTTs=new TextToSpeech(itemView.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int result=mTTs.setLanguage(Locale.UK);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result== TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, itemView.getContext().getString(R.string.not_supported_lang));
                    }else{
                        pronounce_button.setEnabled(true);
                    }
                }else{
                    Log.e(TAG, itemView.getContext().getString(R.string.pron_init_failed));
                }
            }
        });

    }
    public void onBindModel(){
        pronounce_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
        pronouncing_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void speak(){
        String text=pronouncing_text.getText().toString();
        float pitch=(float) pitch_bar.getProgress()/50;
        if(pitch < 0.1) pitch = 0.1f;
        float speed=(float) speed_bar.getProgress()/50;
        if(speed < 0.1) speed = 0.1f;
        mTTs.setPitch(pitch);
        mTTs.setSpeechRate(speed);
        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void finalize() throws Throwable {
        if(mTTs!=null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.finalize();
    }
}
