package com.example.serverandroid.service;

import com.example.serverandroid.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();

    Country saveOrUpdateCounty(Country service);

    Country getCountryById(int id);

    void deleteCountryById(int id);
}
