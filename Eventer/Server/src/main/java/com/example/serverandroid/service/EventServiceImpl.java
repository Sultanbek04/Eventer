package com.example.serverandroid.service;

import com.example.serverandroid.entity.Event;
import com.example.serverandroid.repository.CountryRepository;
import com.example.serverandroid.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional
    public Event saveOrUpdateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteEventById(int id) {
        eventRepository.deleteById(id);
    }

//    @Override
//    @Transactional
//    public List<Event> findAllByCountryIdAndCityNameAndSectionId(int countryId, String cityName, int sectionId) {
//        return eventRepository.findAllByCountryIdAndCityNameAndSectionId(countryId, cityName, sectionId);
//    }

//    @Override
//    @Transactional
//    public List<Event> findAllByCountryIdAndCityName(int countryId, String cityName) {
//        return eventRepository.findAllByCountryIdAndCityName(countryId, cityName);
//    }

    @Override
    @Transactional
    public List<Event> findAllByUserId(int userId) {
        return eventRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public List<Event> findAllByCheckedIsTrue() {
        return eventRepository.findAllByCheckedIsTrue();
    }

    @Override
    @Transactional
    public void updateCheckedToTrue(Integer id) {
        eventRepository.updateCheckedToTrue(id);
    }

    @Override
    @Transactional
    public List<Event> getAllByCheckedIsFalse() {
        return eventRepository.getAllByCheckedIsFalse();
    }
}




