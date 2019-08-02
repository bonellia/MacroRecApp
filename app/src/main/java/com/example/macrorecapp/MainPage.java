package com.example.macrorecapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

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
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void openMyCloud(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, MyCloud.class);
        startActivity(intent);
    }
}
