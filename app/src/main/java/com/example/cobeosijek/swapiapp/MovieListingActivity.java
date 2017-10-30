package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.item_list.MovieAdapter;
import com.example.cobeosijek.swapiapp.response.SwapiMoviesResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.MovieEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListingActivity extends BaseActivity implements OnItemClickListener {

    @BindView(R.id.item_list)
    RecyclerView itemList;

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;

    @BindView(R.id.action_bar_heading)
    TextView actionBarHeading;

    private String categoryId;


    public static Intent getLaunchIntent(Context fromContext) {
        return new Intent(fromContext, MovieListingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_listing);

        setUI();
    }


    @Override
    public void setUI() {
        ButterKnife.bind(this);

        getMovies();
    }

    private void getMovies() {
        // TODO: 30/10/2017  change to movie endpoint
        MovieEndpoint service = BackendFactory.getRetrofitInstance().create(MovieEndpoint.class);
        retrofit2.Call<SwapiMoviesResponse> call = service.getMovies();
        call.enqueue(new Callback<SwapiMoviesResponse>() {
            @Override
            public void onResponse(Call<SwapiMoviesResponse> call, Response<SwapiMoviesResponse> response) {
                showMovies(response);
            }

            @Override
            public void onFailure(Call<SwapiMoviesResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showMovies(Response<SwapiMoviesResponse> results) {

        MovieAdapter adapter = new MovieAdapter();
        adapter.setMovieList(results.body().getResults());

        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        itemList.addItemDecoration(decoration);
        itemList.setLayoutManager(layoutManager);
        itemList.setAdapter(adapter);


        // TODO: 30/10/2017 lazy loading with onScroll listener
    }

    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }


    @Override
    public void onItemClick(String itemId) {
        // TODO: 27/10/2017 send item by id or the whole item object to the details activity
    }


}
