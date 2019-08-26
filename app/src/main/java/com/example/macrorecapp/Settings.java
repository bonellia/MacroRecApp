package com.example.macrorecapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {


    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void openDeviceControl(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, DeviceControl.class);
        startActivity(intent);
    }

    public void openCustomize(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, Customize.class);
        startActivity(intent);
    }

    public void openManuals(View view) {
    }

    public void openMyCloud(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, MyCloud.class);
        startActivity(intent);
    }

    public void openGiveAdvice(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, GiveAdvice.class);
        startActivity(intent);
    }

    public void openReportBug(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ReportBug.class);
        startActivity(intent);
    }

    public void openContactRequest(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ContactRequest.class);
        startActivity(intent);
    }

    public void goBack(View view)  {
        view.startAnimation(buttonClick);
        finish();
    }
}
