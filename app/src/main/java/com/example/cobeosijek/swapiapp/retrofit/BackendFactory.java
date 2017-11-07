package com.example.cobeosijek.swapiapp.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class BackendFactory {

    public static final String BASE_URL = "https://swapi.co/api/";

    private static Retrofit retrofit;
    private static PeopleEndpoint peopleEndpoint;
    private static MovieEndpoint movieEndpoint;
    private static PlanetEndpoint planetEndpoint;
    private static SpeciesEndpoint speciesEndpoint;
    private static StarshipsEndpoint starshipsEndpoint;
    private static VehiclesEndpoint vehiclesEndpoint;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PeopleEndpoint getPeopleEndpoint() {
        if (peopleEndpoint == null) {
            peopleEndpoint = getRetrofitInstance().create(PeopleEndpoint.class);
        }
        return peopleEndpoint;
    }

    public static MovieEndpoint getMovieEndpoint() {
        if (movieEndpoint == null) {
            movieEndpoint = getRetrofitInstance().create(MovieEndpoint.class);
        }
        return movieEndpoint;
    }

    public static PlanetEndpoint getPlanetEndpoint() {
        if (planetEndpoint == null) {
            planetEndpoint = getRetrofitInstance().create(PlanetEndpoint.class);
        }
        return planetEndpoint;
    }

    public static SpeciesEndpoint getSpeciesEndpoint() {
        if (speciesEndpoint == null) {
            speciesEndpoint = getRetrofitInstance().create(SpeciesEndpoint.class);
        }
        return speciesEndpoint;
    }

    public static StarshipsEndpoint getStarshipsEndpoint() {
        if (starshipsEndpoint == null) {
            starshipsEndpoint = getRetrofitInstance().create(StarshipsEndpoint.class);
        }
        return starshipsEndpoint;
    }

    public static VehiclesEndpoint getVehiclesEndpoint() {
        if (vehiclesEndpoint == null) {
            vehiclesEndpoint = getRetrofitInstance().create(VehiclesEndpoint.class);
        }
        return vehiclesEndpoint;
    }

}
