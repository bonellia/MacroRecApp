package com.example.macrorecapp;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class CreateAccount extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_create_account);
        Log.i("test Mail", "Create layout layout has been loaded.");

    }
}
