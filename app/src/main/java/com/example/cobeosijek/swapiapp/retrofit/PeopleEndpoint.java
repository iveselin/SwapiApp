package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.response.SwapiPeopleResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public interface PeopleEndpoint {

    @GET("people/")
    Call<SwapiPeopleResponse> getPeople();
}
