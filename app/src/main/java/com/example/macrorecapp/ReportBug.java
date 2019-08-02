package com.example.macrorecapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class ReportBug extends AppCompatActivity {

    final Context context = this;
    private Button reportBugButton;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    Spinner reportBugSpinner;
    String hintReportBugSpinner = "Select Category";
    String[] contentReportBugSpinner = {"App Issue", "Device Issue", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_bug);
        reportBugSpinner = findViewById(R.id.spinnerCategory);
        reportBugSpinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_layout, contentReportBugSpinner, hintReportBugSpinner));

        reportBugButton = findViewById(R.id.send_button);

        // Add activity layout listener for "Report Bug" Button.
        reportBugButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                reportBugButton.startAnimation(buttonClick);
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_report_bug);

                final Button dialogButton = dialog.findViewById(R.id.dialog_button_ok);
                // Set dialog layout listener for "Okay" Button within dialog.
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogButton.startAnimation(buttonClick);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }
}

