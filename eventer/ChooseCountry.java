package com.example.eventer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.eventer.implementation.CountryImplementation;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ChooseCountry extends AppCompatActivity {

    private CountryAdapter adapter;
    private RecyclerView rvCountry;
    private Button btnApply;

    private Button btnClear;
    private Switch switchAll;


    CountryImplementation countryImplementation = new CountryImplementation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);
        rvCountry = findViewById(R.id.rvCountry);


//        btnClear = findViewById(R.id.btnClear);
//        btnClear.setOnClickListener(this::clearParameters);
//        switchAll = findViewById(R.id.sAll);


        btnApply = findViewById(R.id.btnApplyCountry);

        btnApply.setOnClickListener(view -> {
            adapter = (CountryAdapter) rvCountry.getAdapter();

            Toast.makeText(this, String.valueOf(adapter.getCheckedBoxes().get(1)), Toast.LENGTH_SHORT).show();
        });

        Intent intent = getIntent();
        boolean createEventOrNot = intent.getBooleanExtra("createEvent", false);

        if (createEventOrNot == true) {
//            btnClear.setVisibility(View.INVISIBLE);
//            switchAll.setVisibility(View.INVISIBLE);
            countryImplementation.getAllCountries(rvCountry, this, true);

        } else {
            countryImplementation.getAllCountries(rvCountry, this, false);

        }
    }


    private void clearParameters(View view) {
        countryImplementation.getAllCountries(rvCountry, this, false);
    }

}