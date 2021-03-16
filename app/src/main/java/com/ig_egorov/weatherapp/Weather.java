package com.ig_egorov.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("main")
    private String main;

    public String getMain() {
        return main;
    }
}
