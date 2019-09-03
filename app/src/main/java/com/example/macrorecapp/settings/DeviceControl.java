package com.example.macrorecapp.settings;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.macrorecapp.R;

public class DeviceControl extends AppCompatActivity {

    AlphaAnimation buttonclick = new AlphaAnimation(1F, 0.2F);

    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_control);

        // Set listeners for buttons to show dialogs.
        Button button1 = findViewById(R.id.connection1);
        button1.setOnClickListener(onClickListener);
        Button button2 = findViewById(R.id.connection2);
        button2.setOnClickListener(onClickListener);
        Button button3 = findViewById(R.id.connection3);
        button3.setOnClickListener(onClickListener);
        Button button4 = findViewById(R.id.connection4);
        button4.setOnClickListener(onClickListener);
        Button button5 = findViewById(R.id.device_details1);
        button5.setOnClickListener(onClickListener2);
        Button button6 = findViewById(R.id.device_details2);
        button6.setOnClickListener(onClickListener2);
        Button button7 = findViewById(R.id.device_details3);
        button7.setOnClickListener(onClickListener2);
        Button button8 = findViewById(R.id.device_details4);
        button8.setOnClickListener(onClickListener2);

    }

    private View.OnClickListener onClickListener;

    {
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO DO: Set what to do for different Views v.
                v.startAnimation(buttonclick);
                // Set custom dialog view style.
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_device_dc);

                Button dialogNoButton = dialog.findViewById(R.id.dialog_button_no);
                // Set dialog layout listener for "No" Button.
                dialogNoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(buttonclick);
                        dialog.dismiss();
                    }
                });

                Button dialogYesButton = dialog.findViewById(R.id.dialog_button_yes);
                // Set dialog layout listener for "Yes" Button.
                dialogYesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.startAnimation(buttonclick);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        };
    }

    private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TO DO: Set what to do for different Views v.
            v.startAnimation(buttonclick);
            // Set custom dialog view style.
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_device_details);

            Button dialogRBButton = dialog.findViewById(R.id.dialog_button_report_bug);
            // Set dialog layout listener for "Report Bug" Button.
            dialogRBButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(buttonclick);
                    dialog.dismiss();
                }
            });

            Button dialogCFUButton = dialog.findViewById(R.id.dialog_button_check_update);
            // Set dialog layout listener for "Check For Update" Button.
            dialogCFUButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(buttonclick);
                    dialog.dismiss();
                }
            });

            Button dialogDoneButton = dialog.findViewById(R.id.dialog_button_done);
            // Set dialog layout listener for "Done" Button.
            dialogDoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(buttonclick);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    };


    public void goBack(View view) {
        view.startAnimation(buttonclick);
        finish();
    }
}
