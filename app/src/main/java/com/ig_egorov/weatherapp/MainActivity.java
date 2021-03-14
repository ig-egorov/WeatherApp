package com.ig_egorov.weatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String CHOSEN_CITY = "chosenCity";
    static final String TEMPERATURE_RESULT = "temperature";
    static TextView cityView;
    static TextView temperatureView;
    static String cityName;
    static String mainTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        cityView = (TextView) findViewById(R.id.temporaryCity);
        temperatureView = (TextView) findViewById(R.id.temporaryTemperature);

        cityName = getIntent().getStringExtra(CHOSEN_CITY);
        mainTemperature = getIntent().getStringExtra(TEMPERATURE_RESULT);
        displayResults();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void displayResults() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                cityView.setText(cityName);
                temperatureView.setText(mainTemperature);
            }
        });
    }
}