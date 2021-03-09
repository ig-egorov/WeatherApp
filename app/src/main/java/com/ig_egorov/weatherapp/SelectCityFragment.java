package com.ig_egorov.weatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SelectCityFragment extends Fragment {


    public SelectCityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_city, container, false);
        EditText citySelector = (EditText) view.findViewById(R.id.city_selector);
        Button getWeatherButton = (Button) view.findViewById(R.id.get_weather_button);
        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), MainActivity.class);
                intent.putExtra(MainActivity.CHOSEN_CITY, citySelector.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }

}