package com.example.macrorecapp.features.rotary;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.example.macrorecapp.R;
import com.example.macrorecapp.features.shared.views.RotaryView;

public class RotaryPhotoSettings extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    RotaryView mPhotoRotaryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotary_photo_settings);
        mPhotoRotaryView = findViewById(R.id.rotaryPhotoView);
        mPhotoRotaryView.addTarget(300);


    }

    public void goBack(View view) {
        view.startAnimation(buttonClick);
        finish();
    }

    public void openThreeSixtyPhotoRotary(View view) {
    }
}
