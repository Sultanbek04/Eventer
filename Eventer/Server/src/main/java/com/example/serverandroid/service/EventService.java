package com.example.serverandroid.service;

import com.example.serverandroid.entity.Country;
import com.example.serverandroid.entity.Event;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();


    Event saveOrUpdateEvent(Event event);

    Event getEventById(int id);

    void deleteEventById(int id);

//    List<Event> findAllByCountryIdAndCityNameAndSectionId(int countryId, String cityName, int sectionId);
//
//    List<Event> findAllByCountryIdAndCityName(int countryId, String cityName);

    List<Event> findAllByUserId(int userId);

    List<Event> findAllByCheckedIsTrue();

    void updateCheckedToTrue(Integer id);


    List<Event> getAllByCheckedIsFalse();


}
