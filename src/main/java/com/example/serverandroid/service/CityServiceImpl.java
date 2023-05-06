package com.example.serverandroid.service;

import com.example.serverandroid.entity.City;
import com.example.serverandroid.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    @Override
    @Transactional

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    @Transactional
    public List<City> getAllCitiesById(int countryId) {
        System.out.println(countryId);
        return cityRepository.findAll().stream().filter(s -> s.getCountryId() == countryId).toList();
    }

    @Override
    @Transactional

    public City saveOrUpdateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    @Transactional

    public City getCityById(int id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional

    public void deleteCityById(int id) {
        cityRepository.deleteById(id);
    }
}
