package com.example.serverandroid.service;

import com.example.serverandroid.entity.EventImage;


import java.util.List;

public interface EventImageService {
    List<EventImage> getAllEventImages();

    EventImage saveOrUpdateEventImage(EventImage eventImage);

    EventImage getEventImageById(int id);

    void deleteEventImageById(int id);
}
