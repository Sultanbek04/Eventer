package com.example.eventer.api;


import com.example.eventer.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/users")
    Call<List<User>> getAllUsers();

    @POST("/user")
    Call<User> saveUser(@Body User user);

    @GET("/user/{id}")
    Call<User> getUserById(@Path("id") int id);

    @GET("/user/{email}/{password}")
    Call<User> getUserByEmailAndPassword(@Path("email") String email,
                                         @Path("password") String password);

    @PUT("/user")
    Call<Void> updateUser(@Body User user);

    @DELETE("/user/{id}")
    Call<Void> deleteUser(@Path("id") int id);


}
