package com.example.macrorecapp;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void openLiveControl(View view) {
    }

    public void openMotionControl(View view) {
    }

    public void openTimeLapse(View view) {
    }

    public void openSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openMyCloud(View view) {
    }
}
