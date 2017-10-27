package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.cobeosijek.swapiapp.base.BaseActivity;

import butterknife.ButterKnife;

public class ItemDetailsActivity extends BaseActivity {

    private static final String KEY_ID_SEND = "item_Id";



    public static Intent getLaunchIntent(Context fromContext, String itemId) {
        Intent launchIntent = new Intent(fromContext, ItemDetailsActivity.class);
        launchIntent.putExtra(KEY_ID_SEND, itemId);
        return launchIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        setUI();
    }


    @Override
    public void setUI() {
        ButterKnife.bind(this);
    }
}
