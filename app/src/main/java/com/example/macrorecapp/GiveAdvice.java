package com.example.macrorecapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class GiveAdvice extends AppCompatActivity {

    final Context context = this;
    private Button giveAdviceButton;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    Spinner giveAdviceSpinner;
    String hintGiveAdviceSpinner = "Select Category";
    String[] contextGiveAdviceSpinner = {"App Design", "Feature Request", "Miscellaneous"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_advice);
        giveAdviceSpinner = findViewById(R.id.spinnerCategory);
        giveAdviceSpinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_layout, contextGiveAdviceSpinner, hintGiveAdviceSpinner));

        giveAdviceButton = findViewById(R.id.send_button);

        // Add activity layout listener for "Change Password" Button.
        giveAdviceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                giveAdviceButton.startAnimation(buttonClick);
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_give_advice);

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

