package com.example.macrorecapp.features.authentication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.macrorecapp.R;

public class ForgotPassword extends AppCompatActivity {
    AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void cancelActivity(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void onClickResetPassword(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ResetPassword.class);
        startActivity(intent);
    }
}
