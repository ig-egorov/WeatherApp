package com.ig_egorov.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SelectCityFragment extends Fragment {

    interface Listener {
        void onFragmentButtonClick();
    }

    Listener listener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    public SelectCityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_city, container, false);
        Button getWeatherButton = (Button) view.findViewById(R.id.get_weather_button);
        //Call Listener interface method by clicking on button within fragment
        getWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onFragmentButtonClick();
                }
            }
        });
        return view;
    }

}