package com.ig_egorov.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weather;

    public Main getMain() {
        return main;
    }
    public List<Weather> getWeather() { return weather; }

}
