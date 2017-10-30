package com.example.cobeosijek.swapiapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class Categories {
    List<Category> availableCategories = new ArrayList<>();

    public Categories() {
        availableCategories.add(new Category("Films", "films/", CategoryTypeEnum.FILMS));
        availableCategories.add(new Category("People", "people/", CategoryTypeEnum.PEOPLE));
        availableCategories.add(new Category("Planets", "planets/", CategoryTypeEnum.PLANETS));
        availableCategories.add(new Category("Species", "species/", CategoryTypeEnum.SPECIES));
        availableCategories.add(new Category("Starships", "starships/", CategoryTypeEnum.STARSHIPS));
        availableCategories.add(new Category("Vehicles", "vehicles/", CategoryTypeEnum.VEHICLES));

    }

    public void setAvailableCategories(List<Category> categories) {
        availableCategories = categories;
    }

    public List<Category> getAvailableCategories() {
        return availableCategories;
    }
}
