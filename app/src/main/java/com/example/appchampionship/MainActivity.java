package com.example.appchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnReg(View view) {
        startActivity(new Intent(this, MainLogin.class));

    }

    public void btInLogin(View view) {
        startActivity(new Intent(this, MainLogin.class));
    }
}