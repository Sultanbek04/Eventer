package com.example.eventer.api;


import com.example.eventer.entity.Event;
import com.example.eventer.entity.UserLikedEvents;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface EventApi {
    @GET("/events")
    Call<List<Event>> getAllEvents();

    @POST("/event")
    Call<Event> saveEvent(@Body Event event);

    @PUT("/event")
    Call<Void> updateEvent(@Body Event event);

    @DELETE("/eventLikedByUser/{likedEventId}")
    Call<Void> deleteLikedEventByUser(@Path("likedEventId") int likedEventId);

    @DELETE("/event/{id}")
    Call<Void> deleteEvent(@Path("id") int id);

    @GET("/events/{countryId}/{cityName}/{sectionId}")
    Call<List<Event>> getEventsByGivenParameters(@Path("countryId") int countryId, @Path("cityName") String cityName
            , @Path("sectionId") int sectionId);

    @GET("/events/{countryId}/{cityName}")
    Call<List<Event>> getAllEventsByCountryAndCity(@Path("countryId") int countryId, @Path("cityName") String cityName);

    @GET("/events/{userId}")
    Call<List<Event>> getAllEventsByUserId(@Path("userId") int userId);

    @GET("/unchecked_events")
    Call<List<Event>> getAllByCheckedIsFalse();

    @GET("/checked_events")
    Call<List<Event>> getAllByCheckedIsTrue();

    @GET("/city_id_events/{cityId}")
    Call<List<Event>> getAllEventsByCityId(@Path("cityId") int cityId);

    @GET("/increment_like/{id}")
    Call<Void> incrementLike(@Path("id") int id);

    @GET("/decrement_like/{id}")
    Call<Void> decrementLike(@Path("id") int id);

    @POST("/saveLikedEventByUser")
    Call<UserLikedEvents> saveLikedEventByUser(@Body UserLikedEvents userLikedEvents);

    @GET("/getNumberOfLikes/{id}")
    Call<Integer> getNumberOfLikes(@Path("id") int id);

    @Multipart
    @POST("/upload-image")
    Call<String> uploadImage(@Part MultipartBody.Part body);


    @GET("/change_to_true/{id}")
    Call<Void> changeCheckedToTrue(@Path("id") int id);

    @GET("/events/{cityId}/{sectionId}")
    Call<List<Event>> getEventsByCityIdAndSectionId(@Path("cityId") int cityId,
                                                    @Path("sectionId") int sectionId);


    @GET("/getLikedEventIdByUserId/{userId}")
    Call<List<Integer>> getLikedEventIdByUserId(@Path("userId") Integer userId);

    @GET("checkedEvents/{cityId}")
    Call<List<Event>> getCheckedEventsByCityId(@Path("cityId") int cityId);

    @GET("checkedEvents/{cityId}/{sectionId}")
    Call<List<Event>> getCheckedEventsByCityIdAndSectionId(@Path("cityId") int cityId, @Path("sectionId") int sectionId);

    @GET("getEventsOfUser/{userId}")
    Call<List<Event>> getEventsOfUser(@Path("userId") int userId);

}
