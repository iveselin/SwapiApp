package com.example.cobeosijek.swapiapp.retrofit;

import com.example.cobeosijek.swapiapp.response.SwapiStarshipsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ivan on 4.11.2017..
 */

public interface StarshipsEndpoint {
    @GET("starships/")
    Call<SwapiStarshipsResponse> getStarships();

    @GET("starships/")
    Call<SwapiStarshipsResponse> getStarship(@Query("search") String name);

    @GET("starships/")
    Call<SwapiStarshipsResponse> getNextPage(@Query("page") String nextPage);
}
