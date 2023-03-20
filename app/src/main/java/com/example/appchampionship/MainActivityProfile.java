package com.example.appchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
    }

    public void nextClick(View view) {

        startActivity(new Intent(this, Menu.class));
    }

    public void exitClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void Listen(View view) {
        startActivity(new Intent(this, MainList.class));
    }

    public void Profile(View view) {
        startActivity(new Intent(this, MainActivityProfile.class));
    }
}