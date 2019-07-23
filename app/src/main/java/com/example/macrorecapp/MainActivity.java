package com.example.macrorecapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends Activity {

    SharedPreferences sharedpreferences;
    public static final String PrefName = "macrorespref";
    public static final String EmailKey = "emailKey";
    public static final String PassKey = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Removes notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void onLoginWithGoogle(View view) {
        Intent intent = new Intent(this, LoginWithGoogle.class);
        startActivity(intent);
    }

    public void onLoginWithFacebook(View view) {
        Intent intent = new Intent(this, LoginWithFacebook.class);
        startActivity(intent);
    }


    public void onLoginWithInstagram(View view) {
        Intent intent = new Intent(this, LoginWithInstagram.class);
        startActivity(intent);
    }

    public void onClickLogIn(View view) {
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void recoverPassword(View view) {
        Intent intent = new Intent(this, PasswordRecovery.class);
        startActivity(intent);
    }
}
