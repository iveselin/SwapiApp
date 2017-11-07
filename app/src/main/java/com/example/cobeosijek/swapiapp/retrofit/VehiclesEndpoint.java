package com.example.cobeosijek.swapiapp.retrofit;


import com.example.cobeosijek.swapiapp.models.Vehicle;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ivan on 4.11.2017..
 */

public interface VehiclesEndpoint {

    @GET("vehicles/")
    Call<SwapiResponse<Vehicle>> getVehicles();

    @GET("vehicles/")
    Call<SwapiResponse<Vehicle>> getVehicle(@Query("search") String name);

    @GET("vehicles/")
    Call<SwapiResponse<Vehicle>> getNextPage(@Query("page") String nextPage);
}
