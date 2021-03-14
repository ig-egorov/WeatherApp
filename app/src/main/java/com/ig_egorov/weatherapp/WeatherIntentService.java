package com.ig_egorov.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherIntentService extends IntentService {

    private static final String API_KEY = "d18b91d1dc1f0fdefb57a6b492b8bbb5";
    private String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private Retrofit retrofit;
    private Call<WeatherResponse> call;
    static final String CHOSEN_CITY = "city";
    private String city;
    private String temperatureResult;


    public WeatherIntentService() {
        super("WeatherIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        city = intent.getExtras().getString(CHOSEN_CITY);
        getCall();
        getTemperature();
        stopSelf();
    }


    public void getCall() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetWeatherInterface getWeatherInterface = retrofit.create(GetWeatherInterface.class);
        call = getWeatherInterface.getWeather(city, "metric", API_KEY);
    }

    public void getTemperature() {
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (!response.isSuccessful()) {
                    temperatureResult = response.message();
                    return;
                }
                WeatherResponse weatherResponse = response.body();
                Main mainBlock = weatherResponse.getMain();
                double temp = mainBlock.getTemp();
                temperatureResult = Double.toString(temp);
                Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dialogIntent.putExtra(MainActivity.CHOSEN_CITY, city);
                dialogIntent.putExtra(MainActivity.TEMPERATURE_RESULT, temperatureResult);
                getApplication().startActivity(dialogIntent);
                Log.v("Temperature", temperatureResult);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                temperatureResult = t.getMessage();
            }
        });
    }
}