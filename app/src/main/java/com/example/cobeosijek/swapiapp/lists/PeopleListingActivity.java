package com.example.cobeosijek.swapiapp.lists;

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

import com.example.cobeosijek.swapiapp.ItemDetailsActivity;
import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.category_list.CategoryTypeEnum;
import com.example.cobeosijek.swapiapp.item_list.PersonAdapter;
import com.example.cobeosijek.swapiapp.response.SwapiPeopleResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.PeopleEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleListingActivity extends BaseActivity implements OnItemClickListener, OnLastItemReachedListener {

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;

    @BindView(R.id.action_bar_heading)
    TextView actionBarHeading;

    private String nextLink;

    private PersonAdapter adapter;

    public static Intent getLaunchIntent(Context fromContext) {
        return new Intent(fromContext, PeopleListingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_listing);

        setUI();
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);

        getPeople();
    }

    private void getPeople() {

        PeopleEndpoint service = BackendFactory.getPeopleEndpoint();
        Call<SwapiPeopleResponse> call = service.getPeople();
        call.enqueue(new Callback<SwapiPeopleResponse>() {
            @Override
            public void onResponse(Call<SwapiPeopleResponse> call, Response<SwapiPeopleResponse> response) {
                nextLink = response.body().getNext();
                showPeople(response);
            }

            @Override
            public void onFailure(Call<SwapiPeopleResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPeople(Response<SwapiPeopleResponse> results) {
        adapter = new PersonAdapter();
        adapter.setPersonList(results.body().getResults());
        adapter.setOnItemListener(this);
        adapter.setLastItemReachedListener(this);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        itemList.addItemDecoration(decoration);
        itemList.setLayoutManager(layoutManager);
        itemList.setAdapter(adapter);
    }

    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }


    @Override
    public void onItemClick(String itemId) {
        startActivity(ItemDetailsActivity.getLaunchIntent(this, itemId, CategoryTypeEnum.PEOPLE.name()));
    }


    @Override
    public void onLastItem() {
        if (nextLink != null) {

            Uri uri = Uri.parse(nextLink);
            String nextPageNumber = uri.getQueryParameter("page");

            PeopleEndpoint service = BackendFactory.getPeopleEndpoint();
            Call<SwapiPeopleResponse> call = service.getNextPage(nextPageNumber);
            call.enqueue(new Callback<SwapiPeopleResponse>() {
                @Override
                public void onResponse(Call<SwapiPeopleResponse> call, Response<SwapiPeopleResponse> response) {
                    nextLink = response.body().getNext();
                    addPeople(response);
                }

                @Override
                public void onFailure(Call<SwapiPeopleResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "No more data to load", Toast.LENGTH_SHORT).show();
        }
    }

    private void addPeople(Response<SwapiPeopleResponse> response) {
        adapter.addPersonList(response.body().getResults());
    }
}
