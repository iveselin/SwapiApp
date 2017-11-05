package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.category_list.CategoryAdapter;
import com.example.cobeosijek.swapiapp.category_list.Categories;
import com.example.cobeosijek.swapiapp.category_list.CategoryTypeEnum;
import com.example.cobeosijek.swapiapp.lists.MovieListingActivity;
import com.example.cobeosijek.swapiapp.lists.PeopleListingActivity;
import com.example.cobeosijek.swapiapp.lists.PlanetListingActivity;
import com.example.cobeosijek.swapiapp.lists.SpeciesListingActivity;
import com.example.cobeosijek.swapiapp.lists.StarshipsListingActivity;
import com.example.cobeosijek.swapiapp.lists.VehiclesListingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.category_list)
    RecyclerView categoryList;

    public static Intent getLaunchIntent(Context fromContext) {
        return new Intent(fromContext, CategoryActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setUI();
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);

        CategoryAdapter categoryAdapter = new CategoryAdapter();
        categoryAdapter.setCategoryList(new Categories().getAvailableCategories());
        categoryAdapter.setOnItemClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        categoryList.setLayoutManager(layoutManager);

        categoryList.setAdapter(categoryAdapter);
    }


    @Override
    public void onItemClick(String itemId) {

        if (itemId.equals(CategoryTypeEnum.PEOPLE.name())) {
            startActivity(PeopleListingActivity.getLaunchIntent(this));
        } else if (itemId.equals(CategoryTypeEnum.FILMS.name())) {
            startActivity(MovieListingActivity.getLaunchIntent(this));
        } else if (itemId.equals(CategoryTypeEnum.PLANETS.name())) {
            startActivity(PlanetListingActivity.getLaunchIntent(this));
        } else if (itemId.equals(CategoryTypeEnum.SPECIES.name())) {
            startActivity(SpeciesListingActivity.getLaunchIntent(this));
        } else if (itemId.equals(CategoryTypeEnum.STARSHIPS.name())) {
            startActivity(StarshipsListingActivity.getLaunchIntent(this));
        } else if (itemId.equals(CategoryTypeEnum.VEHICLES.name())) {
            startActivity(VehiclesListingActivity.getLaunchIntent(this));
        }
    }
}
