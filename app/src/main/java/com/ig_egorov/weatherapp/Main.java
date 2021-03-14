package com.ig_egorov.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    private double temp;

    public double getTemp() {
        return temp;
    }
}
