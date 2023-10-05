package com.example.serverandroid.repository;

import com.example.serverandroid.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryRepository extends JpaRepository<Country, Integer> {
}
