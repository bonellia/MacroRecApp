package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class LIIChangeVerification extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    private Button verifyButton;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liichange_verification);

        verifyButton = findViewById(R.id.verifyButton);

        // Add activity layout listener for "Verify" Button.
        verifyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                verifyButton.startAnimation(buttonClick);
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update_credentials);

                final Button dialogButton = dialog.findViewById(R.id.dialog_button_ok);
                // Set dialog layout listener for "Okay" Button within dialog.
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogButton.startAnimation(buttonClick);
                        dialog.dismiss();
                        Intent intent = new Intent(context, AccountSettings.class);
                        startActivity(intent);
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
