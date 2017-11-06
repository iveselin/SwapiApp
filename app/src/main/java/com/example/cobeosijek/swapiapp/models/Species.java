package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class Species extends BaseModel {

    private String name;

    private String classification;

    private String language;

    @SerializedName("average_lifespan")
    private String averageLifespan;

    public String getName() {
        return getValueOrEmpty(name);
    }

    public String getClassification() {
        return getValueOrEmpty(classification);
    }

    public String getLanguage() {
        return getValueOrEmpty(language);
    }

    public String getAverageLifespan() {
        return getValueOrEmpty(averageLifespan);
    }
}
