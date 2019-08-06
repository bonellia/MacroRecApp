package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class PersonalInformation extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }
}
