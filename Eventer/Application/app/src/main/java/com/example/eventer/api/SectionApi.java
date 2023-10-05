package com.example.eventer.api;



import com.example.eventer.entity.Section;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SectionApi {
    @GET("/sections")
    Call<List<Section>> getAllSections();

    @POST("/section")
    Call<Section> saveSection(@Body Section section);

    @PUT("/section")
    Call<Void> updateSection(@Body Section section);

    @DELETE("/section/{id}")
    Call<Void> deleteSection(@Path("id") int id);
}
