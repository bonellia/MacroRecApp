package com.example.macrorecapp;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;

public class MyCloud extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cloud);
    }

    public void goBack(View view) {
        finish();
    }

    public void goSettings(View view) {
    }
}
