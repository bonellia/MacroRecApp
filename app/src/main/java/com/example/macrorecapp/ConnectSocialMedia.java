package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class ConnectSocialMedia extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_social_media);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void onConnectGoogle(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ConnectGoogle.class);
        startActivity(intent);
    }
    public void onConnectFacebook(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ConnectFacebook.class);
        startActivity(intent);
    }
    public void onConnectInstagram(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ConnectInstagram.class);
        startActivity(intent);
    }
}
