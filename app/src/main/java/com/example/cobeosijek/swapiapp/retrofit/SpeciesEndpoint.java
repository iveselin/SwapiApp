package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.response.SwapiPlanetResponse;
import com.example.cobeosijek.swapiapp.response.SwapiSpeciesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ivan on 4.11.2017..
 */

public interface SpeciesEndpoint {
    @GET("species/")
    Call<SwapiSpeciesResponse> getSpecies();

    @GET("species/")
    Call<SwapiSpeciesResponse> getSpecie(@Query("search") String name);

    @GET("species/")
    Call<SwapiSpeciesResponse> getNextPage(@Query("page") String nextPage);
}
