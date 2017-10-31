package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class Species extends BaseModel {

    private String name;

    private String classification;

    private String language;

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getClassification() {
        return getValueOrEmpty(classification);
    }

    public String getLanguage() {
        return getValueOrEmpty(language);
    }
}
