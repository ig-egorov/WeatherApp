package com.ig_egorov.weatherapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

public class WeatherService extends Service {


    private final IBinder binder = new WeatherBinder();

    public class WeatherBinder extends Binder {
        private String cityName = "Moscow";

        WeatherService getWeatherService() {
            return WeatherService.this;
        }

        public String getCity() {
            return this.cityName;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


}