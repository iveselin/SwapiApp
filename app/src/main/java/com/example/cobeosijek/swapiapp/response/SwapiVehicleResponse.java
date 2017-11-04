package com.example.cobeosijek.swapiapp.response;

import com.example.cobeosijek.swapiapp.models.Person;
import com.example.cobeosijek.swapiapp.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4.11.2017..
 */

public class SwapiVehicleResponse {

    private int count;
    private String next;
    private String previous;
    private List<Vehicle> results;

    public SwapiVehicleResponse() {
        this.results = new ArrayList<Vehicle>();
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

    public List<Vehicle> getResults() {
        return results;
    }
}
