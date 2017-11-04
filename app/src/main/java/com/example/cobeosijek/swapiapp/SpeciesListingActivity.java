package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.item_list.SpeciesAdapter;
import com.example.cobeosijek.swapiapp.response.SwapiSpeciesResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.SpeciesEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeciesListingActivity extends BaseActivity implements OnLastItemReachedListener, OnItemClickListener {

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;

    @BindView(R.id.action_bar_heading)
    TextView actionBarHeading;

    private String nextLink;

    private SpeciesAdapter adapter;

    public static Intent getLaunchIntent(Context fromContext) {
        return new Intent(fromContext, SpeciesListingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_listing);

        setUI();
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);

        getSpecies();
    }

    private void getSpecies() {
        SpeciesEndpoint service = BackendFactory.getSpeciesEndpoint();
        Call<SwapiSpeciesResponse> call = service.getSpecies();
        call.enqueue(new Callback<SwapiSpeciesResponse>() {
            @Override
            public void onResponse(Call<SwapiSpeciesResponse> call, Response<SwapiSpeciesResponse> response) {
                nextLink = response.body().getNext();
                showSpecies(response);
            }

            @Override
            public void onFailure(Call<SwapiSpeciesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSpecies(Response<SwapiSpeciesResponse> response) {
        adapter = new SpeciesAdapter();
        adapter.setSpeciesList(response.body().getResults());
        adapter.setOnItemListener(this);
        adapter.setLastItemReachedListener(this);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        itemList.addItemDecoration(decoration);
        itemList.setLayoutManager(layoutManager);
        itemList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String itemId) {
        startActivity(ItemDetailsActivity.getLaunchIntent(this, itemId));
    }

    @Override
    public void onLastItem() {
        if (nextLink != null) {

            Uri uri = Uri.parse(nextLink);
            String nextPageNumber = uri.getQueryParameter("page");

            SpeciesEndpoint service = BackendFactory.getSpeciesEndpoint();
            Call<SwapiSpeciesResponse> call = service.getNextPage(nextPageNumber);
            call.enqueue(new Callback<SwapiSpeciesResponse>() {
                @Override
                public void onResponse(Call<SwapiSpeciesResponse> call, Response<SwapiSpeciesResponse> response) {
                    nextLink = response.body().getNext();
                    addSpecies(response);
                }

                @Override
                public void onFailure(Call<SwapiSpeciesResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No more data to load", Toast.LENGTH_SHORT).show();
        }
    }

    private void addSpecies(Response<SwapiSpeciesResponse> response) {
        adapter.addSpeciesList(response.body().getResults());
    }

    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }
}
