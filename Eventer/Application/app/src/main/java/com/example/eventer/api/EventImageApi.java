package com.example.eventer.api;




import com.example.eventer.entity.Event;
import com.example.eventer.entity.EventImage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EventImageApi {
    @GET("/events_images")
    Call<List<EventImage>> getAllEventsImages();

    @POST("/event")
    Call<Event> saveEvent(@Body Event event);

    @PUT("/event")
    Call<Void> updateEvent(@Body Event event);

    @DELETE("/event/{id}")
    Call<Void> deleteEvent(@Path("id") int id);
}
