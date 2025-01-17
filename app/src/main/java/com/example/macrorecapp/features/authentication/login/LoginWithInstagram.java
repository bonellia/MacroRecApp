package com.example.macrorecapp.features.authentication.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;

import com.example.macrorecapp.features.MainPage;
import com.example.macrorecapp.R;

public class LoginWithInstagram extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_instagram);
    }

    public void cancelLogin(View view) {
        finish();
    }
    public void openMainPage(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
}
