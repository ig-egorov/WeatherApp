package com.ig_egorov.weatherapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

public class WeatherService extends Service {

    private String cityName = "Moscow";
    private final IBinder binder = new WeatherBinder();

    public class WeatherBinder extends Binder {
        WeatherService getWeatherService() {
            return WeatherService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getCity() {
        return this.cityName;
    }
}