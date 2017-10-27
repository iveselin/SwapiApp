package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.category_list.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ItemListingActivity extends BaseActivity implements OnItemClickListener {

    private static final String KEY_CATEGORY_ID = "category_ID";

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;

    @BindView(R.id.action_bar_heading)
    TextView actionBarHeading;

    public static Intent getLaunchIntent(Context fromContext, String categoryId) {
        Intent launchIntent = new Intent(fromContext, ItemListingActivity.class);
        launchIntent.putExtra(KEY_CATEGORY_ID, categoryId);
        return launchIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listing);

        setUI();
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }


    @Override
    public void onItemClick(String itemId) {
        // TODO: 27/10/2017 send item by id to the details activity 
    }
}
