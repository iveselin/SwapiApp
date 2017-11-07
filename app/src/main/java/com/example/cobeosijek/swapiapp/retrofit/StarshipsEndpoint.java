package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.models.Starship;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ivan on 4.11.2017..
 */

public interface StarshipsEndpoint {
    @GET("starships/")
    Call<SwapiResponse<Starship>> getStarships();

    @GET("starships/")
    Call<SwapiResponse<Starship>> getStarship(@Query("search") String name);

    @GET("starships/")
    Call<SwapiResponse<Starship>> getNextPage(@Query("page") String nextPage);
}
