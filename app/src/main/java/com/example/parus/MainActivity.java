package com.example.parus;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pg;
    HelpersAdapter adapter;
    public static final String RECOGNITION_TYPE="recogn";
    public static final String VOICE_RECOGNITION="V_R";
    private final int VOICE_REQUEST_CODE=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        pg=findViewById(R.id.viewpager);
        adapter = new HelpersAdapter(this,this);
        pg.setAdapter(adapter);
        try{
            onCatchIntent(getIntent());
        }
        catch (NullPointerException exception){
            exception.getSuppressed();
        }
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(navView, navController);
    }


    public void onCatchIntent(Intent intent){
        if (intent!=null) {
            switch (intent.getStringExtra(RECOGNITION_TYPE)) {
                case VOICE_RECOGNITION:
                    pg.setCurrentItem(1);
                    Intent voice_intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                            Locale.getDefault());
                    if (intent.resolveActivity(getPackageManager()) == null) {
                        Toast.makeText(this, getString(R.string.voice_rec_denied),
                                Toast.LENGTH_LONG).show();
                    } else {
                        startActivityForResult(voice_intent, VOICE_REQUEST_CODE);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case VOICE_REQUEST_CODE:
                if (resultCode==RESULT_OK && data!=null){
                    ArrayList<String> voice_result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    HearingHolder hear=(HearingHolder)adapter.holders.get(1);
                    hear.speech_text.setText(voice_result.get(0));

                }
        }
    }
}