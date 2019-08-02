package com.example.macrorecapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyCloud extends AppCompatActivity {
    final Context context = this;
    private Button logOutButton;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cloud);

        logOutButton = findViewById(R.id.log_out);

        // Add activity layout listener for "Log Out" Button.
        logOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                logOutButton.startAnimation(buttonClick);
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_logout_confirmation);

                final Button dialogNoButton = dialog.findViewById(R.id.dialog_button_no);
                // Set dialog layout listener for "No" Button within dialog.
                dialogNoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogNoButton.startAnimation(buttonClick);
                        dialog.dismiss();
                    }
                });

                final Button dialogYesButton = dialog.findViewById(R.id.dialog_button_yes);
                // Set dialog layout listener for "Yes" Button within dialog.
                dialogYesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogYesButton.startAnimation(buttonClick);
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        });

    }

    public void goBack(View view) {
        view.setAnimation(buttonClick);
        finish();
    }

    public void onPersonalInformationClick(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(context, PersonalInformation.class);
        startActivity(intent);
    }
}
