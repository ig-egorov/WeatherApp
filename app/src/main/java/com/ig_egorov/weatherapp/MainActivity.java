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

    static final String CHOSEN_CITY = null;
    private WeatherService weatherService;
    private boolean isBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WeatherService.WeatherBinder binder = (WeatherService.WeatherBinder) service;
            weatherService = binder.getWeatherService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        displayCity();

        //String cityName = getIntent().getExtras().getString(CHOSEN_CITY);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WeatherService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }

    public void displayCity() {
        final TextView textView = (TextView) findViewById(R.id.temporaryText);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String cityName;
                if (isBound && weatherService != null) {
                    cityName = weatherService.getCity();
                    textView.setText(cityName);
                } else if (isBound == false && weatherService != null) {
                    Log.v("Error 1", "No Binding");
                } else if (weatherService == null && isBound){
                    Log.v("Error 2", "No Service");
                } else {
                    Log.v("Error 3", "Neither");
                }
            }
        });
    }
}