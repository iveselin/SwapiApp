package com.example.cobeosijek.swapiapp.response;

import com.example.cobeosijek.swapiapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class SwapiMoviesResponse {
    private int count;
    private String next;
    private String previous;

    private List<Movie> results;

    public SwapiMoviesResponse() {
        this.results = new ArrayList<Movie>();
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

    public List<Movie> getResults() {
        return results;
    }
}
