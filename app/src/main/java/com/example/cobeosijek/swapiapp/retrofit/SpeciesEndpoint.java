package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.models.Species;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ivan on 4.11.2017..
 */

public interface SpeciesEndpoint {
    @GET("species/")
    Call<SwapiResponse<Species>> getSpecies();

    @GET("species/")
    Call<SwapiResponse<Species>> getSpecie(@Query("search") String name);

    @GET("species/")
    Call<SwapiResponse<Species>> getNextPage(@Query("page") String nextPage);
}
