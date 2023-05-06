package com.example.eventer.api;




import com.example.eventer.entity.City;
import com.example.eventer.entity.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CityApi {
    @GET("/cities")
    Call<List<City>> getAllCities();

    @POST("/city")
    Call<City> saveCity(@Body Section city);

    @PUT("/city")
    Call<Void> updateCity(@Body Section city);

    @GET("/cities/{countryId}")
    Call<List<City>> getAllCitiesByCountryId(@Path("countryId") int countryId);

    @DELETE("/city/{id}")
    Call<Void> deleteCity(@Path("id") int id);
}
