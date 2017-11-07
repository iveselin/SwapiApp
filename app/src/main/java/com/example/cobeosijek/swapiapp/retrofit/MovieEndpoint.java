package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.models.Movie;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public interface MovieEndpoint {

    @GET("films/")
    Call<SwapiResponse<Movie>> getMovies();

    @GET("films/")
    Call<SwapiResponse<Movie>> getMovieById(@Query("search") String title);
}
