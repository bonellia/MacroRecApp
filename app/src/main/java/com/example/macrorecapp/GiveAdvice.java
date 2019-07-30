package com.example.macrorecapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class GiveAdvice extends Activity {

    Spinner spinner;
    String defaultTextForSpinner = "Select Category";
    String[] arrayForSpinner = {"App Design", "Feature Request", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_advice);
        spinner = findViewById(R.id.spinnerCategory);
        spinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_layout, arrayForSpinner, defaultTextForSpinner));
    }

    public void goBack(View view) {
        finish();
    }
}

