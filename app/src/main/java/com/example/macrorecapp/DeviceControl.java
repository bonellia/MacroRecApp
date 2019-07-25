package com.example.macrorecapp;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;

public class DeviceControl extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_control);
    }

    public void goBack(View view) {
        finish();
    }
}
