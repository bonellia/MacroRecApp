package com.example.macrorecapp.features.cloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.macrorecapp.features.cloud.accountsettings.ChangeLogInInformation;
import com.example.macrorecapp.features.cloud.accountsettings.ConnectSocialMedia;
import com.example.macrorecapp.R;

public class AccountSettings extends AppCompatActivity {

    AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void goConnectSocialMedia(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(context, ConnectSocialMedia.class);
        startActivity(intent);
    }

    public void onClickPrivacy(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(context, Privacy.class);
        startActivity(intent);
    }

    public void onClickChangeLII(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(context, ChangeLogInInformation.class);
        startActivity(intent);
    }
}
