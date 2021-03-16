package com.ig_egorov.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    private double temp;

    @SerializedName("feels_like")
    private double feels_like;

    public double getTemp() {
        return temp;
    }
    public double getFeels_like() { return feels_like; }
}
