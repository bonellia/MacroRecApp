package com.example.macrorecapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PasswordRecovery extends AppCompatActivity {

    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        button = findViewById(R.id.resetPassButton);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog_reset_pass
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_reset_pass);
                dialog.setTitle("Title...");

                Button dialogButton = dialog.findViewById(R.id.dialog_button_ok);
                // if button is clicked, close the custom dialog_reset_pass
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

    public void cancelActivity(View view) {
        finish();
    }

    public void resetPassword(View view) {
    }
}
