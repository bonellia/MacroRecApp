package com.example.macrorecapp;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void openDeviceControl(View view) {
        Intent intent = new Intent(this, DeviceControl.class);
        startActivity(intent);
    }

    public void openCustomize(View view) {
        Intent intent = new Intent(this, Customize.class);
        startActivity(intent);
    }

    public void openManuals(View view) {
    }

    public void openMyCloud(View view) {
        Intent intent = new Intent(this, MyCloud.class);
        startActivity(intent);
    }

    public void openGiveAdvice(View view) {
        Intent intent = new Intent(this, GiveAdvice.class);
        startActivity(intent);
    }

    public void openReportBug(View view) {
    }

    public void openContactRequest(View view) {
    }

    public void goBack(View view)  {
        finish();
    }
}
