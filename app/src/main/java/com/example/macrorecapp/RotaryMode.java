package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class RotaryMode extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotary_mode);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void goRotaryVideo(View view) {
        view.startAnimation(buttonClick);
    }

    public void goRotaryPhoto(View view) {
        view.startAnimation(buttonClick);
    }
}
