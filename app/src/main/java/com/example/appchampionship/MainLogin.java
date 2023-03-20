package com.example.appchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
    }

    public void BtnPr(View view) {

        startActivity(new Intent(this, MainActivityProfile.class));
    }

    public void btSignIn(View view) {

        startActivity(new Intent(this, MainActivityMask.class));    }
}