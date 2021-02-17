package com.example.parus.autorising;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parus.R;

import java.util.Objects;

public class AuthorisingActivity extends AppCompatActivity {
    NetworkInfo info;
    AlertDialog alertDialog;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
       // Toolbar toolbar = findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater li=LayoutInflater.from(this);
    }

    public void checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        if (info == null || !info.isConnected() || !info.isAvailable()) {
            handler.sendEmptyMessage(1);
        } else {
            handler.sendEmptyMessage(2);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkConnection();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();

    }

    @Override
    protected void onPause() {
        super.onPause();
        checkConnection();
    }

    @Override
    protected void onStop() {
        super.onStop();
        checkConnection();
    }
}
