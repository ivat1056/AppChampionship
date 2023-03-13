package com.example.appchampionship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
    void Get(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI api = retrofit.create(RetrofitAPI.class);
    }

    public void nextClick(View view) {


    }

    public void NLogin(View view) {

    }
}