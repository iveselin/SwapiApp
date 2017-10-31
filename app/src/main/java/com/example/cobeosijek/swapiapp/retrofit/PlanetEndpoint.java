package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.response.SwapiPlanetResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public interface PlanetEndpoint {

    @GET("planets/")
    Call<SwapiPlanetResponse> getPlanets();

    @GET("planets/")
    Call<SwapiPlanetResponse> getPerson(@Query("search") String name);

    @GET("planets/")
    Call<SwapiPlanetResponse> getNextPage(@Query("page") String nextPage);
}
