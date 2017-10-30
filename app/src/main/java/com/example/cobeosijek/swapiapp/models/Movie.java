package com.example.cobeosijek.swapiapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class Movie {

    private String title;

    @SerializedName("episode_id")
    private int episodeId;

    private String producer;

    @SerializedName("opening_crawl")
    private String openingCrawl;

    public String getTitle() {
        return title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getProducer() {
        return producer;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }
}
