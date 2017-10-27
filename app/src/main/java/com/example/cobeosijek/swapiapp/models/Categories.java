package com.example.cobeosijek.swapiapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class Categories {
    List<Category> availibleCategories=new ArrayList<>();

    public Categories() {
        availibleCategories.add(new Category("Films", "films/"));
        availibleCategories.add(new Category("People", "people/"));
        availibleCategories.add(new Category("Planets", "planets/"));
        availibleCategories.add(new Category("Species", "species/"));
        availibleCategories.add(new Category("Starships", "starships/"));
        availibleCategories.add(new Category("Vehicles", "vehicles/"));

    }

    public void setAvailibleCategories(List<Category> categories) {
        availibleCategories = categories;
    }

    public List<Category> getAvailibleCategories() {
        return availibleCategories;
    }
}
