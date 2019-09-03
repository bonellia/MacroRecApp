package com.example.macrorecapp.features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;

import com.example.macrorecapp.R;
import com.example.macrorecapp.features.cloud.MyCloud;
import com.example.macrorecapp.features.livecontrol.LiveControl;
import com.example.macrorecapp.features.motioncontrol.MotionControl;
import com.example.macrorecapp.features.rotary.RotaryMode;
import com.example.macrorecapp.settings.Settings;

public class MainPage extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void openLiveControl(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, LiveControl.class);
        startActivity(intent);
    }

    public void openMotionControl(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, MotionControl.class);
        startActivity(intent);
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

    public void openRotary(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, RotaryMode.class);
        startActivity(intent);
    }
}
