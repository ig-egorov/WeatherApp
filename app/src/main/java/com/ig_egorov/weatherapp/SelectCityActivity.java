package com.ig_egorov.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SelectCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        SelectCityFragment selectCityFragment = (SelectCityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_select_city);
    }

}