package com.example.cobeosijek.swapiapp.models;

import java.io.Serializable;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public enum CategoryTypeEnum implements Serializable {
    FILMS,
    PEOPLE,
    PLANETS,
    SPECIES,
    STARSHIPS,
    VEHICLES;

    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
