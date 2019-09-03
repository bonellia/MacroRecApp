package com.example.macrorecapp.features.authentication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.macrorecapp.features.authentication.register.CreateAccount;
import com.example.macrorecapp.features.authentication.ForgotPassword;
import com.example.macrorecapp.features.MainPage;
import com.example.macrorecapp.R;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String PrefName = "macrorespref";
    public static final String EmailKey = "emailKey";
    public static final String PassKey = "passKey";
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.2F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("test Mail", "Main activity layout has been loaded.");

        // Creates placeholder views to check credential preferences
        EditText emailView = findViewById(R.id.emailField);
        EditText passView = findViewById(R.id.passField);
        sharedpreferences = getSharedPreferences(PrefName, Context.MODE_PRIVATE);


        if (sharedpreferences.contains(EmailKey)) {
            emailView.setText(sharedpreferences.getString(EmailKey, ""));
        }
        if (sharedpreferences.contains(PassKey)) {
            passView.setText(sharedpreferences.getString(PassKey, ""));
        }

    }

    public void onCreateAccount(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void onLoginWithGoogle(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, LoginWithGoogle.class);
        startActivity(intent);
    }

    public void onLoginWithFacebook(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, LoginWithFacebook.class);
        startActivity(intent);
    }


    public void onLoginWithInstagram(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, LoginWithInstagram.class);
        startActivity(intent);
    }

    public void onClickLogIn(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void recoverPassword(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }
}
