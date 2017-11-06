package com.example.cobeosijek.swapiapp.category_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class Categories {

    public static List<Category> getAvailableCategories() {
        List<Category> availableCategories = new ArrayList<>();

        availableCategories.add(new Category("Films", "films/", CategoryTypeEnum.FILMS));
        availableCategories.add(new Category("People", "people/", CategoryTypeEnum.PEOPLE));
        availableCategories.add(new Category("Planets", "planets/", CategoryTypeEnum.PLANETS));
        availableCategories.add(new Category("Species", "species/", CategoryTypeEnum.SPECIES));
        availableCategories.add(new Category("Starships", "starships/", CategoryTypeEnum.STARSHIPS));
        availableCategories.add(new Category("Vehicles", "vehicles/", CategoryTypeEnum.VEHICLES));

        return availableCategories;
    }
}
