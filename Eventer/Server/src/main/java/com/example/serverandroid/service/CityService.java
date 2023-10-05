package com.example.serverandroid.service;

import com.example.serverandroid.entity.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    List<City> getAllCitiesById(int id);

    City saveOrUpdateCity(City city);

    City getCityById(int id);

    void deleteCityById(int id);
}
