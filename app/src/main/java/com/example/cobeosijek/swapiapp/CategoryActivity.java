package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.category_list.CategoryAdapter;
import com.example.cobeosijek.swapiapp.category_list.OnItemClickListener;
import com.example.cobeosijek.swapiapp.models.Categories;

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
        categoryAdapter.setCategoryList(new Categories().getAvailibleCategories());
        categoryAdapter.setOnItemClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        categoryList.setLayoutManager(layoutManager);

        categoryList.setAdapter(categoryAdapter);
    }

    @Override
    public void onItemClick(String itemId) {
        startActivity(ItemListingActivity.getLaunchIntent(this, itemId));
    }
}
