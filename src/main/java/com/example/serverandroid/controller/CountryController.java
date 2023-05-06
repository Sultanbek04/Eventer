package com.example.serverandroid.controller;

import com.example.serverandroid.entity.Country;
import com.example.serverandroid.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/country/{id}")
    public Country getCountryById(@PathVariable("id") int id) {
        return countryService.getCountryById(id);
    }

    @PostMapping("/country")
    public Country addCountry(@RequestBody Country service) {
        return countryService.saveOrUpdateCounty(service);
    }

    @PutMapping("/country")
    public void updateCountry(@RequestBody Country service) {
        countryService.saveOrUpdateCounty(service);
    }

    @DeleteMapping("/country/{id}")
    public void deleteCountry(@PathVariable int id) {
        countryService.deleteCountryById(id);
    }
}
