package com.example.serverandroid.service;

import com.example.serverandroid.entity.EventImage;
import com.example.serverandroid.repository.EventImageRepository;
import com.example.serverandroid.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventImageServiceImpl implements EventImageService {

    @Autowired
    private EventImageRepository eventImageRepository;

    @Override
    public List<EventImage> getAllEventImages() {
        return eventImageRepository.findAll();
    }

    @Override
    public EventImage saveOrUpdateEventImage(EventImage eventImage) {
        return eventImageRepository.save(eventImage);
    }

    @Override
    public EventImage getEventImageById(int id) {
        return eventImageRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteEventImageById(int id) {
        eventImageRepository.deleteById(id);
    }
}
