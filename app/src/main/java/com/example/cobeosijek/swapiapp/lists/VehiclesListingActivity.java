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
import com.example.cobeosijek.swapiapp.item_list.VehiclesAdapter;
import com.example.cobeosijek.swapiapp.models.Vehicle;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.VehiclesEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehiclesListingActivity extends BaseActivity implements OnLastItemReachedListener, OnItemClickListener {

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;

    @BindView(R.id.action_bar_heading)
    TextView actionBarHeading;

    private String nextLink;

    private VehiclesAdapter adapter;

    public static Intent getLaunchIntent(Context fromContext) {
        return new Intent(fromContext, VehiclesListingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_listing);

        setUI();
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);

        getVehicles();
    }

    private void getVehicles() {
        VehiclesEndpoint service = BackendFactory.getVehiclesEndpoint();
        Call<SwapiResponse<Vehicle>> call = service.getVehicles();
        call.enqueue(new Callback<SwapiResponse<Vehicle>>() {
            @Override
            public void onResponse(Call<SwapiResponse<Vehicle>> call, Response<SwapiResponse<Vehicle>> response) {
                nextLink = response.body().getNext();
                showSpecies(response);
            }

            @Override
            public void onFailure(Call<SwapiResponse<Vehicle>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSpecies(Response<SwapiResponse<Vehicle>> response) {
        adapter = new VehiclesAdapter();
        adapter.setVehicleList(response.body().getResults());
        adapter.setOnItemListener(this);
        adapter.setLastItemReachedListener(this);

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        itemList.addItemDecoration(decoration);
        itemList.setLayoutManager(layoutManager);
        itemList.setAdapter(adapter);
    }

    @Override
    public void onLastItem() {
        if (nextLink != null) {

            Uri uri = Uri.parse(nextLink);
            String nextPageNumber = uri.getQueryParameter("page");

            VehiclesEndpoint service = BackendFactory.getVehiclesEndpoint();
            Call<SwapiResponse<Vehicle>> call = service.getNextPage(nextPageNumber);
            call.enqueue(new Callback<SwapiResponse<Vehicle>>() {
                @Override
                public void onResponse(Call<SwapiResponse<Vehicle>> call, Response<SwapiResponse<Vehicle>> response) {
                    nextLink = response.body().getNext();
                    addSpecies(response);
                }

                @Override
                public void onFailure(Call<SwapiResponse<Vehicle>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), R.string.no_more_data_text, Toast.LENGTH_SHORT).show();
        }
    }

    private void addSpecies(Response<SwapiResponse<Vehicle>> response) {
        adapter.addVehiclesList(response.body().getResults());
    }

    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }

    @Override
    public void onItemClick(String itemId) {
        startActivity(ItemDetailsActivity.getLaunchIntent(this, itemId, CategoryTypeEnum.VEHICLES));
    }
}
