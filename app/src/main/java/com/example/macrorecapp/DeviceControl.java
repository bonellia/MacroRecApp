package com.example.macrorecapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeviceControl extends Activity {

    final Context context = this;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_control);

        // Set listeners for buttons to show dialogs.
        button1 = findViewById(R.id.connection1);
        button1.setOnClickListener(onClickListener);
        button2 = findViewById(R.id.connection2);
        button2.setOnClickListener(onClickListener);
        button3 = findViewById(R.id.connection3);
        button3.setOnClickListener(onClickListener);
        button4 = findViewById(R.id.connection4);
        button4.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener;

    {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO: Set what to do for different Views v.

                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_device_dc);

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
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        };
    }


    public void goBack(View view) {
        finish();
    }
}
