package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PasswordRecovery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void cancelActivity(View view) {
        finish();
    }

    public void resetPassword(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
