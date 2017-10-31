package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class Planet extends BaseModel {

    private String name;

    private String population;

    @SerializedName("rotation_period")
    private String rotationPeriod;

    @SerializedName("orbital_period")
    private String orbitalPeriod;

    private String terrain;

    public String getName() {
        return name;
    }

    public String getPopulation() {
        return population;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getTerrain() {
        return terrain;
    }
}
