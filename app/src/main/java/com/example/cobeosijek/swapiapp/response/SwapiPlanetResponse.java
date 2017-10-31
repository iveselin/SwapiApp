package com.example.cobeosijek.swapiapp.response;

import com.example.cobeosijek.swapiapp.models.Planet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class SwapiPlanetResponse {

    private int count;
    private String next;
    private String previous;
    private List<Planet> results;

    public SwapiPlanetResponse() {
        this.results = new ArrayList<Planet>();
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Planet> getResults() {
        return results;
    }
}
