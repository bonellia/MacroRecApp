package com.example.macrorecapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccount extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);
    Spinner countrySpinner;
    String hintCountrySpinner = "Select Country";
    String[] contentCountrySpinner = {"Turkey", "United Kingdom", "United States"};
    Spinner proficiencySpinner;
    String hintProficiencySpinner = "Select Proficiency";
    String[] contentProficiencySpinner = {"Designer", "Engineer", "Photographer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        countrySpinner = findViewById(R.id.spinnerCountry);
        countrySpinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_layout, contentCountrySpinner, hintCountrySpinner));
        proficiencySpinner = findViewById(R.id.spinnerProficiency);
        proficiencySpinner.setAdapter(new CustomSpinnerAdapter(this, R.layout.spinner_layout, contentProficiencySpinner, hintProficiencySpinner));
    }

    public void onVerifyAccountClick(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, AccountVerification.class);
        startActivity(intent);
    }
}
