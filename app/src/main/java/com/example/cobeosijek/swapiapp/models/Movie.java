package com.example.cobeosijek.swapiapp.models;

import com.example.cobeosijek.swapiapp.base.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class Movie extends BaseModel {

    private String title;

    @SerializedName("episode_id")
    private int episodeId;

    private String producer;

    @SerializedName("opening_crawl")
    private String openingCrawl;

    public String getTitle() {
        return getValueOrEmpty(title);
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getProducer() {
        return getValueOrEmpty(producer);
    }

    public String getOpeningCrawl() {
        return getValueOrEmpty(openingCrawl);
    }
}
