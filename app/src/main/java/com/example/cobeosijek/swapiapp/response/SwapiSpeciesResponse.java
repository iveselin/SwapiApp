package com.example.cobeosijek.swapiapp.response;

import android.service.textservice.SpellCheckerService;

import com.example.cobeosijek.swapiapp.models.Person;
import com.example.cobeosijek.swapiapp.models.Species;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4.11.2017..
 */

public class SwapiSpeciesResponse {
    private int count;
    private String next;
    private String previous;
    private List<Species> results;

    public SwapiSpeciesResponse() {
        this.results = new ArrayList<Species>();
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

    public List<Species> getResults() {
        return results;
    }
}
