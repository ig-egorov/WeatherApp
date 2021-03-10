package com.ig_egorov.weatherapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final String CHOSEN_CITY = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView textView = (TextView) findViewById(R.id.temporaryText);
        String cityName = getIntent().getExtras().getString(CHOSEN_CITY);
        textView.setText(cityName);
    }
}