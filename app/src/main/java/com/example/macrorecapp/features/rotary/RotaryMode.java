package com.example.macrorecapp.features.rotary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.macrorecapp.R;

public class RotaryMode extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    private Context context = this;

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
        Intent intent = new Intent(context, RotaryPhotoSettings.class);
        startActivity(intent);
    }
}
