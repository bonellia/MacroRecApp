package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class ThreeSixtyPhotoRotary extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_sixty_photo_rotary);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(context, AccountSettings.class);
        startActivity(intent);
    }
}

