package com.example.cobeosijek.swapiapp.response;

import com.example.cobeosijek.swapiapp.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class SwapiPeopleResponse {

    private int count;
    private String next;
    private String previous;

    private List<Person> results;

    public SwapiPeopleResponse() {
        this.results = new ArrayList<Person>();
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

    public List<Person> getResults() {
        return results;
    }
}
