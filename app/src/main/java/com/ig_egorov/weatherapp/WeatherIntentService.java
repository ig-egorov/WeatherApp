package com.ig_egorov.weatherapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

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
        getMainWeatherResults();
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

    public void getMainWeatherResults() {
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                String mainTemp = "";
                String feelsLike = "";
                List<Weather> weatherList = null;
                String skyState = "";
                if (!response.isSuccessful()) {
                    mainTemp = response.message();
                    Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    dialogIntent.putExtra(MainActivity.CHOSEN_CITY, city);
                    dialogIntent.putExtra(MainActivity.TEMPERATURE_RESULT, mainTemp);
                    return;
                }
                WeatherResponse weatherResponse = response.body();

                Main mainBlock = weatherResponse.getMain();
                weatherList = weatherResponse.getWeather();

                mainTemp = Double.toString(mainBlock.getTemp());
                feelsLike = Double.toString(mainBlock.getFeels_like());

                skyState = weatherList.get(0).getMain();

                Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dialogIntent.putExtra(MainActivity.CHOSEN_CITY, city);
                dialogIntent.putExtra(MainActivity.TEMPERATURE_RESULT, mainTemp);
                dialogIntent.putExtra(MainActivity.FEELS_LIKE_RESULT, feelsLike);
                dialogIntent.putExtra(MainActivity.SKY_IS, skyState);
                getApplication().startActivity(dialogIntent);
                Log.v("Temperature", mainTemp);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                temperatureResult = t.getMessage();
                Log.v("Error", temperatureResult);
            }
        });
    }
}
