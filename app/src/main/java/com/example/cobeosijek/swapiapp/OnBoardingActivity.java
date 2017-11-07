package com.example.cobeosijek.swapiapp;

import android.os.Bundle;

import com.example.cobeosijek.swapiapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnBoardingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        setUI();
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.forward_icon)
    void onForwardClick() {
        startActivity(CategoryActivity.getLaunchIntent(this));
        finish();
    }
}
