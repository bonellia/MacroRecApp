package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class RotaryPhotoSettings extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotary_photo_settings);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void openThreeSixtyPhotoRotary(View view) {
    }
}
