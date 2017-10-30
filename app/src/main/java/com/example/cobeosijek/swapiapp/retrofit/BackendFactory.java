package com.example.cobeosijek.swapiapp.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class BackendFactory {

    public static final String BASE_URL = "https://swapi.co/api/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



}
