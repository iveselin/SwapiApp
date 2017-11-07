package com.example.cobeosijek.swapiapp.lists;

import android.content.Context;
import android.content.Intent;
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
import com.example.cobeosijek.swapiapp.category_list.CategoryTypeEnum;
import com.example.cobeosijek.swapiapp.item_list.MovieAdapter;
import com.example.cobeosijek.swapiapp.models.Movie;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;
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
        MovieEndpoint service = BackendFactory.getMovieEndpoint();
        Call<SwapiResponse<Movie>> call = service.getMovies();
        call.enqueue(new Callback<SwapiResponse<Movie>>() {
            @Override
            public void onResponse(Call<SwapiResponse<Movie>> call, Response<SwapiResponse<Movie>> response) {
                showMovies(response);
            }

            @Override
            public void onFailure(Call<SwapiResponse<Movie>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showMovies(Response<SwapiResponse<Movie>> results) {

        MovieAdapter adapter = new MovieAdapter();
        adapter.setMovieList(results.body().getResults());
        adapter.setOnItemListener(this);

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
        startActivity(ItemDetailsActivity.getLaunchIntent(this, itemId, CategoryTypeEnum.FILMS));
    }
}
