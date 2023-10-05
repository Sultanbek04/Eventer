package com.example.serverandroid.controller;

import com.example.serverandroid.entity.City;
import com.example.serverandroid.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/cities/{countryId}")
    public List<City> getAllCitiesByCountryId(@PathVariable("countryId") int countryId) {
        return cityService.getAllCitiesById(countryId);
    }

    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable("id") int id) {
        return cityService.getCityById(id);
    }

    @PostMapping("/city")
    public City addCity(@RequestBody City city) {
        return cityService.saveOrUpdateCity(city);
    }

    @PutMapping("/city")
    public void updateCity(@RequestBody City city) {
        cityService.saveOrUpdateCity(city);
    }

    @DeleteMapping("/city/{id}")
    public void deleteCity(@PathVariable int id) {
        cityService.deleteCityById(id);
    }

//    @GetMapping("/employees/lastName/{lastName}")
//    public List<City> getEmployeesByLastname(@PathVariable("lastName") String lastName) {
//        return employeeService.getEmployeesByLastName(lastName);
//    }

}
