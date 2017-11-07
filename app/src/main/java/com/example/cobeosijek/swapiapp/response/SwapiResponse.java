package com.example.cobeosijek.swapiapp.response;

import com.example.cobeosijek.swapiapp.base.BaseModel;

import java.util.List;

/**
 * Created by cobeosijek on 07/11/2017.
 */

public class SwapiResponse<T extends BaseModel> {

    private int count;
    private String next;
    private String previous;
    private List<T> results;


    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<T> getResults() {
        return results;
    }
}
