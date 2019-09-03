package com.example.macrorecapp.features.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.example.macrorecapp.R;

public class ResetPassword extends AppCompatActivity {

    final Context context = this;
    private Button changePassButton;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        changePassButton = findViewById(R.id.change_password_button);

        // Add activity layout listener for "Change Password" Button.
        changePassButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                changePassButton.startAnimation(buttonClick);
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_reset_pass);

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

    public void showPassword(View view) {
    }

    public void showRepeatPassword(View view) {
    }

}
