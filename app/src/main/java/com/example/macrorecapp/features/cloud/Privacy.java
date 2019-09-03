package com.example.macrorecapp.features.cloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.macrorecapp.R;
import com.example.macrorecapp.features.cloud.AccountSettings;

public class Privacy extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
    }

    public void onClickDone(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(context, AccountSettings.class);
        startActivity(intent);
    }
}
