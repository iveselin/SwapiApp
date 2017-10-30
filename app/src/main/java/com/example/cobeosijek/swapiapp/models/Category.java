package com.example.cobeosijek.swapiapp.models;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class Category {

    private String categoryName;
    private String categoryURL;
    private CategoryTypeEnum categoryType;

    public Category(String categoryName, String categoryURL, CategoryTypeEnum categoryType) {
        this.categoryName = categoryName;
        this.categoryURL = categoryURL;
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryURL() {
        return categoryURL;
    }

    public void setCategoryURL(String categoryURL) {
        this.categoryURL = categoryURL;
    }

    public CategoryTypeEnum getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryTypeEnum categoryType) {
        this.categoryType = categoryType;
    }
}
