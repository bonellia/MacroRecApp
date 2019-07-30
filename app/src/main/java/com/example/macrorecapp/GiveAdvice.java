package com.example.macrorecapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class GiveAdvice extends Activity {

    final Context context = this;
    private Button button;

    Spinner spinner;
    String defaultTextForSpinner = "Select Category";
    String[] arrayForSpinner = {"App Design", "Feature Request", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_advice);
        spinner = findViewById(R.id.spinnerCategory);
        spinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_layout, arrayForSpinner, defaultTextForSpinner));

        button = findViewById(R.id.send_button);

        // Add activity layout listener for "Change Password" Button.
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_give_advice);

                Button dialogButton = dialog.findViewById(R.id.dialog_button_ok);
                // Set dialog layout listener for "Okay" Button.
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void goBack(View view) {
        finish();
    }
}

