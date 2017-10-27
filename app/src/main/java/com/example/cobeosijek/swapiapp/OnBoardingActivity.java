package com.example.cobeosijek.swapiapp;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.cobeosijek.swapiapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnBoardingActivity extends BaseActivity {

    @BindView(R.id.forward_icon)
    ImageView forwardImage;

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
    }
}
