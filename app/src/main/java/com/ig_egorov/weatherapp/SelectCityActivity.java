package com.ig_egorov.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SelectCityActivity extends AppCompatActivity implements SelectCityFragment.Listener{

    protected String cityName;
    EditText citySelector;
    SelectCityFragment selectCityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        selectCityFragment = (SelectCityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_select_city);
        citySelector = (EditText) selectCityFragment.getView().findViewById(R.id.city_selector);
    }

    //Defining cityName variable of activity by taking String from EditText field using click on a button and putting it into intent
    @Override
    public void onFragmentButtonClick() {
        cityName = citySelector.getText().toString();
        //Temporary decision to understand the logic and code better selector
        if (TextUtils.isEmpty(cityName)) {
            citySelector.setError("Please, select your city");
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.CHOSEN_CITY, cityName);
            startActivity(intent);
        }
    }
}