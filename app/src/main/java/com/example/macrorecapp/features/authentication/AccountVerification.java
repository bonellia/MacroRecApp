package com.example.macrorecapp.features.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import com.example.macrorecapp.R;
import com.example.macrorecapp.features.MainPage;

public class AccountVerification extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);
    }

    public void onClickProceed(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }
}
