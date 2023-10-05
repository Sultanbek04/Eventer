package com.example.serverandroid.repository;

import com.example.serverandroid.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {

}
