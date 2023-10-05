package com.example.eventer;


import com.example.eventer.api.CityApi;
import com.example.eventer.api.CountryApi;
import com.example.eventer.api.EventApi;
import com.example.eventer.api.EventImageApi;
import com.example.eventer.api.SectionApi;
import com.example.eventer.api.UserApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private Retrofit retrofit;
    private static final String BASE_URL = "http://10.1.198.153:55555/";

    private NetworkService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public CityApi getCityApi() {
        return retrofit.create(CityApi.class);
    }

    public CountryApi getCountryApi() {
        return retrofit.create(CountryApi.class);
    }

    public SectionApi getSectionApi() {
        return retrofit.create(SectionApi.class);
    }

    public UserApi getUserApi() {
        return retrofit.create(UserApi.class);
    }

    public EventApi getEventApi() {
        return retrofit.create(EventApi.class);
    }



}
