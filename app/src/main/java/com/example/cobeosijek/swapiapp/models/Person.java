package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class Person extends BaseModel {

    private String name;

    @SerializedName("birth_year")
    private String birthYear;

    private String gender;

    private String mass;

    private String height;

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getBirthYear() {
        return getValueOrEmpty(birthYear);
    }

    public String getGender() {
        return getValueOrEmpty(gender);
    }

    public String getMass() {
        return mass;
    }

    public String getHeight() {
        return height;
    }
}
