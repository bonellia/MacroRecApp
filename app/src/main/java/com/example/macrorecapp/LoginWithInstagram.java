package com.example.macrorecapp;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class LoginWithInstagram extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login_with_instagram);
    }

    public void cancelLogin(View view) {
        finish();
    }
    public void openMainPage(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}
