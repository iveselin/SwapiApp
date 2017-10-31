package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class Vehicle extends BaseModel {

    private String name;

    private String model;

    @SerializedName("vehicle_class")
    private String vehicleClass;

    private String crew;

    private String passengers;

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getModel() {
        return getValueOrEmpty(model);
    }

    public String getVehicleClass() {
        return getValueOrEmpty(vehicleClass);
    }

    public String getCrew() {
        return getValueOrEmpty(crew);
    }

    public String getPassengers() {
        return getValueOrEmpty(passengers);
    }
}
