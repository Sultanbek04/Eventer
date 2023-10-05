package com.example.eventer.implementation;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventer.CityAdapter;
import com.example.eventer.CountryAdapter;
import com.example.eventer.NetworkService;
import com.example.eventer.R;
import com.example.eventer.api.CityApi;
import com.example.eventer.entity.City;
import com.example.eventer.entity.Country;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityImplementation {

    List<City> cityList = new ArrayList<>();

    public void getAllCitiesByCountryId(int countryId, Context context, RecyclerView rvCity, boolean isPostEvent) {
        NetworkService
                .getInstance()
                .getCityApi()
                .getAllCitiesByCountryId(countryId)
                .enqueue(new Callback<List<City>>() {
                    @Override
                    public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        rvCity.setLayoutManager(layoutManager);
                        rvCity.setAdapter(new CityAdapter(context, R.layout.city, response.body(), isPostEvent));
                        return;
                    }

                    @Override
                    public void onFailure(Call<List<City>> call, Throwable t) {
                        cityList.add(new City(1, 1, "Hello"));
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                });

    }
}
