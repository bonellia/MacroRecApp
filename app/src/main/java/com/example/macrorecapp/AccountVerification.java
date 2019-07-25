package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AccountVerification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_verification);
    }

    public void goBack(View view) {
        finish();
    }

    public void showPassword(View view) {
    }

    public void showRepeatPassword(View view) {
    }

}
