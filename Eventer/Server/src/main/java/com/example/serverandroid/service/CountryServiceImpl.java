package com.example.serverandroid.service;

import com.example.serverandroid.entity.Country;
import com.example.serverandroid.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Override
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional
    public Country saveOrUpdateCounty(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Transactional
    public Country getCountryById(int id) {
        return countryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteCountryById(int id) {
        countryRepository.deleteById(id);
    }


}
