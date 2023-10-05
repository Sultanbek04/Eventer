package com.example.serverandroid.controller;


import com.example.serverandroid.entity.Event;
import com.example.serverandroid.entity.UserLikedEvents;
import com.example.serverandroid.repository.EventRepository;
import com.example.serverandroid.repository.UserLikedEventsRepository;
import com.example.serverandroid.service.EventService;
import com.example.serverandroid.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/upload-image")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Картинка прилетела");
        imageStorageService.uploadImage(file);
    }

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserLikedEventsRepository userLikedEventsRepository;

    @Autowired
    private ImageStorageService imageStorageService;

//    @GetMapping("/events/{cityId}/{sectionId}")
//    public List<Event> findAllByCountryIdAndCityNameAndSectionId(
//            @PathVariable("cityId") String cityId, @PathVariable("sectionId") int sectionId) {
//
//        return eventService.findAllByCountryIdAndCityNameAndSectionId(countryId, cityName, sectionId);
//
//    }

    @GetMapping("/events/{userId}")
    public List<Event> findAllByUserId(@PathVariable("userId") int userId) {
        return eventService.findAllByUserId(userId);
    }

    @GetMapping("/unchecked_events")
    public List<Event> getAllByCheckedIsFalse() {
        return eventService.getAllByCheckedIsFalse();
    }

    @GetMapping("/checked_events")
    public List<Event> getAllByCheckedIsTrue() {
        return eventRepository.getAllByCheckedIsTrue();
    }

    @GetMapping("/city_id_events/{cityId}")
    public List<Event> getAllEventsByCityId(@PathVariable("cityId") int cityId) {
        return eventRepository.getEventsByCityId(cityId);
    }

    @GetMapping("/events/{cityId}/{sectionId}")
    public List<Event> getAllEventsByCityId(@PathVariable("cityId") int cityId, @PathVariable("sectionId") int sectionId) {
        return eventRepository.getEventsByCityIdAndSectionId(cityId, sectionId);
    }

    @GetMapping("/change_to_true/{id}")
    public void changeCheckedToTrue(@PathVariable("id") int id) {
        eventService.updateCheckedToTrue(id);
    }

    @GetMapping("/increment_like/{id}")
    public void incrementLike(@PathVariable("id") int id) {
        eventRepository.incrementNumberOfLikesOfEvent(id);
    }

    @GetMapping("/decrement_like/{id}")
    public void decrementLike(@PathVariable("id") int id) {
        eventRepository.decrementNumberOfLikesOfEvent(id);
    }

    @PostMapping("/saveLikedEventByUser")
    public UserLikedEvents saveLikedEventByUser(@RequestBody UserLikedEvents userLikedEvents) {
        return userLikedEventsRepository.save(userLikedEvents);
    }

    @GetMapping("/getNumberOfLikes/{id}")
    public int getNumberOfLikes(@PathVariable("id") int id) {
        return eventRepository.getNumberOfLikesWithSpecificId(id);
    }
//
//    @GetMapping("/events/{countryId}/{cityName}")
//    public List<Event> getAllEventsByCountryAndCity(@PathVariable("countryId") int countryId, @PathVariable("cityName") String cityName) {
//        return eventService.findAllByCountryIdAndCityName(countryId, cityName);
//    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(value = "/download-image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable("name") String name) {
        return imageStorageService.downloadImage(name);
    }

    @GetMapping("/event/{id}")
    public Event getEventById(@PathVariable("id") int id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/event")
    public Event addEvent(@RequestBody Event service) {
        return eventService.saveOrUpdateEvent(service);
    }

    @PutMapping("/event")
    public void updateEvent(@RequestBody Event service) {
        eventService.saveOrUpdateEvent(service);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable int id) {
        eventService.deleteEventById(id);
    }

    @DeleteMapping("/eventLikedByUser/{likedEventId}")
    public void deleteLikedEventByUser(@PathVariable("likedEventId") int likedEventId) {
        userLikedEventsRepository.deleteByLikedEventId(likedEventId);
    }

    @GetMapping("checkedEvents/{cityId}")
    public List<Event> getCheckedEventsByCityId(@PathVariable("cityId") int cityId) {
        return eventRepository.getEventsByCityIdAndCheckedIsTrue(cityId);
    }

    @GetMapping("checkedEvents/{cityId}/{sectionId}")
    public List<Event> getCheckedEventsByCityIdAndSectionId(@PathVariable("cityId") int cityId, @PathVariable("sectionId")
    int sectionId) {
        return eventRepository.getEventsByCityIdAndSectionIdAndCheckedIsTrue(cityId, sectionId);
    }


    @GetMapping("/getLikedEventIdByUserId/{userId}")
    public List<Integer> getLikedEventIdByUserId(@PathVariable("userId") int userId) {
        return userLikedEventsRepository.getLikedEventIdByUserId(userId);
    }

    @GetMapping("/getEventsOfUser/{userId}")
    public ArrayList<Event> getEventsOfUser(@PathVariable("userId") int userId) {
        return eventRepository.getEventsOfUser(userId);
    }
}
