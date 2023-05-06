package com.example.eventer.api;




import com.example.eventer.entity.Country;
import com.example.eventer.entity.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CountryApi {
    @GET("/countries")
    Call<List<Country>> getAllCountries();

    @POST("/country")
    Call<Country> saveCountry(@Body Section section);

    @PUT("/country")
    Call<Void> updateCountry(@Body Section section);

    @DELETE("/country/{id}")
    Call<Void> deleteCountry(@Path("id") int id);
}
