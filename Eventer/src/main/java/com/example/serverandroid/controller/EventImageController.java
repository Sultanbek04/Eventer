package com.example.serverandroid.controller;

import com.example.serverandroid.entity.EventImage;
import com.example.serverandroid.service.EventImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventImageController {
    @Autowired
    private EventImageService eventImageService;

    @GetMapping("/events_images")
    public List<EventImage> getAllEvents() {
        return eventImageService.getAllEventImages();
    }

    @GetMapping("/event_image/{id}")
    public EventImage getEventById(@PathVariable("id") int id) {
        return eventImageService.getEventImageById(id);
    }

    @PostMapping("/event_image")
    public EventImage addEvent(@RequestBody EventImage eventImage) {
        return eventImageService.saveOrUpdateEventImage(eventImage);
    }

    @PutMapping("/event_image")
    public void updateEvent(@RequestBody EventImage eventImage) {
        eventImageService.saveOrUpdateEventImage(eventImage);
    }

    @DeleteMapping("/event_image/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventImageService.deleteEventImageById(id);
    }
}
