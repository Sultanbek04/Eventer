package com.example.eventer.implementation;

import android.content.Context;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventer.CountryAdapter;
import com.example.eventer.NetworkService;
import com.example.eventer.R;
import com.example.eventer.entity.Country;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CountryImplementation {
    List<Country> countryList = new ArrayList<>();

    public void getAllCountries(RecyclerView rvCountry, Context context, boolean isPostEvent) {
        NetworkService
                .getInstance()
                .getCountryApi()
                .getAllCountries()
                .enqueue(new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        rvCountry.setLayoutManager(layoutManager);
                        rvCountry.setAdapter(new CountryAdapter(context, R.layout.country, response.body(), isPostEvent));
                        return;
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {
                        countryList.add(new Country(1, "Hello"));
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
}
