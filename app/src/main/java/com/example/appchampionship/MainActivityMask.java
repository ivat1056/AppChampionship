package com.example.appchampionship;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityMask extends AppCompatActivity {

    final static String userVariableKey = "USER_VARIABLE";
    private BlocMask AdapterQ;
    private List<Model> listQ = new ArrayList<>();
    private AdapterMaskFeeling dataRVAdapter;
    private List<MaskFeeling> listFeeling = new ArrayList<>();
    ImageView image;
    TextView nameUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ListView ivProducts = findViewById(R.id.lvQuotes);
        AdapterQ = new BlocMask(MainActivityMask.this, listQ);
        ivProducts.setAdapter(AdapterQ);
        new GetQuotes().execute();

        RecyclerView rvFeeling = findViewById(R.id.recyclerView);
        rvFeeling.setHasFixedSize(true);
        rvFeeling.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



        dataRVAdapter = new AdapterMaskFeeling(listFeeling, MainActivityMask.this);
        rvFeeling.setAdapter(dataRVAdapter);
        new GetFeeling().execute();


    }

    public void nextClick(View view) {
        startActivity(new Intent(this, Menu.class));
    }

    public void Listen(View view) {
        startActivity(new Intent(this, MainList.class));
    }


    private class GetFeeling extends AsyncTask<Void, Void, String> { // Вывод списка ощущений

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/feelings");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listFeeling.clear();
                dataRVAdapter.notifyDataSetChanged();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskFeeling tempProduct = new MaskFeeling(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getInt("position")
                    );
                    listFeeling.add(tempProduct);
                    dataRVAdapter.notifyDataSetChanged();
                }
                listFeeling.sort(Comparator.comparing(MaskFeeling::getPosition));
                dataRVAdapter.notifyDataSetChanged();
            }
            catch (Exception exception)
            {
                Toast.makeText(MainActivityMask.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private class GetQuotes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/quotes");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception ex)
            {
                return  null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listQ.clear();
                AdapterQ.notifyDataSetInvalidated();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    Model tempProduct = new Model(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getString("description")
                    );
                    listQ.add(tempProduct);
                    AdapterQ.notifyDataSetInvalidated();
                }
            }
            catch (Exception exception)
            {
                Toast.makeText(MainActivityMask.this, "Ошибка!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}