package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.response.SwapiPeopleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public interface PeopleEndpoint {

    @GET("people/")
    Call<SwapiPeopleResponse> getPeople();

    @GET("people/")
    Call<SwapiPeopleResponse> getPerson(@Query("search") String name);
}
