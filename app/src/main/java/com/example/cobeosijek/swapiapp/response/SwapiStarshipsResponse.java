package com.example.cobeosijek.swapiapp.response;

import com.example.cobeosijek.swapiapp.models.Species;
import com.example.cobeosijek.swapiapp.models.Starship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4.11.2017..
 */

public class SwapiStarshipsResponse {
    private int count;
    private String next;
    private String previous;
    private List<Starship> results;

    public SwapiStarshipsResponse() {
        this.results = new ArrayList<Starship>();
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

    public List<Starship> getResults() {
        return results;
    }
}
