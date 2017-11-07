package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.models.Person;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public interface PeopleEndpoint {

    @GET("people/")
    Call<SwapiResponse<Person>> getPeople();

    @GET("people/")
    Call<SwapiResponse<Person>> getPerson(@Query("search") String name);

    @GET("people/")
    Call<SwapiResponse<Person>> getNextPage(@Query("page") String nextPage);
}
