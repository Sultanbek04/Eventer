package com.example.eventer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventer.implementation.CityImplementation;

public class ChooseCity extends AppCompatActivity {
    private CityAdapter adapter;
    private RecyclerView rvCity;

    private Button btnApply;
    CityImplementation cityImplementation = new CityImplementation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        Intent intent = getIntent();
        int countryId = intent.getIntExtra("countryId", -1);
        boolean isPostEvent = intent.getBooleanExtra("isPostEvent", false);
        rvCity = findViewById(R.id.rvCity);
        btnApply = findViewById(R.id.btnApply);

        btnApply.setOnClickListener(this::back);
        cityImplementation.getAllCitiesByCountryId(countryId, this, rvCity, isPostEvent);


    }

    private void back(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}