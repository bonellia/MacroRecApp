package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class AccountSettings extends AppCompatActivity {

    AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void goConnectSocialMedia(View view) {
    }
}
