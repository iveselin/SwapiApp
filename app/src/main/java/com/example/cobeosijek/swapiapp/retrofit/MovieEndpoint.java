package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.response.SwapiMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public interface MovieEndpoint {

    @GET("films/")
    Call<SwapiMoviesResponse> getMovies();
}
