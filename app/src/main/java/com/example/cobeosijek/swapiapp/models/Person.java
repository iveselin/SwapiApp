package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class Person extends BaseModel {

    private String name;

    @SerializedName("birth_year")
    private String birthYear;

    private List<String> films = new ArrayList<>();

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getBirthYear() {
        return getValueOrEmpty(birthYear);
    }

    public List<String> getFilms() {
        return films;
    }
}
