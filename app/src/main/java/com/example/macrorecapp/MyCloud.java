package com.example.macrorecapp;

import android.app.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyCloud extends Activity {
    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cloud);

        button = findViewById(R.id.log_out);

        // Add activity layout listener for "Log Out" Button.
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_logout_confirmation);

                Button dialogNoButton = dialog.findViewById(R.id.dialog_button_no);
                // Set dialog layout listener for "No" Button.
                dialogNoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button dialogYesButton = dialog.findViewById(R.id.dialog_button_yes);
                // Set dialog layout listener for "Yes" Button.
                dialogYesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                    }
                });

                dialog.show();
            }
        });

    }

    public void goBack(View view) {
        finish();
    }

    public void goSettings(View view) {
    }

}
