package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class Starship extends BaseModel {

    private String name;

    private String model;

    @SerializedName("starship_class")
    private String starshipClass;

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getModel() {
        return getValueOrEmpty(model);
    }

    public String getStarshipClass() {
        return getValueOrEmpty(starshipClass);
    }
}
