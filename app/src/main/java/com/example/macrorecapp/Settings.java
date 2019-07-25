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
    }

    public void openManuals(View view) {
    }

    public void openMyCloud(View view) {
    }

    public void openGiveAdvice(View view) {
    }

    public void openReportBug(View view) {
    }

    public void openContactRequest(View view) {
    }

    public void goBack(View view)  {
        finish();
    }
}
