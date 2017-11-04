package com.example.cobeosijek.swapiapp.retrofit;


import com.example.cobeosijek.swapiapp.response.SwapiVehicleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ivan on 4.11.2017..
 */

public interface VehiclesEndpoint {

    @GET("vehicles/")
    Call<SwapiVehicleResponse> getVehicles();

    @GET("vehicles/")
    Call<SwapiVehicleResponse> getVehicle(@Query("search") String name);

    @GET("vehicles/")
    Call<SwapiVehicleResponse> getNextPage(@Query("page") String nextPage);
}
