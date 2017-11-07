package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.models.Planet;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public interface PlanetEndpoint {

    @GET("planets/")
    Call<SwapiResponse<Planet>> getPlanets();

    @GET("planets/")
    Call<SwapiResponse<Planet>> getPlanet(@Query("search") String name);

    @GET("planets/")
    Call<SwapiResponse<Planet>> getNextPage(@Query("page") String nextPage);
}
