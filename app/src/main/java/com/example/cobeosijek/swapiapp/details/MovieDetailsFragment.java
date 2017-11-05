package com.example.cobeosijek.swapiapp.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.models.Movie;
import com.example.cobeosijek.swapiapp.response.SwapiMoviesResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.MovieEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 5.11.2017..
 */

public class MovieDetailsFragment extends Fragment {

    private static final String KEY_MOVIE_ID = "movie_id";

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.movie_episode)
    TextView movieEpisode;

    @BindView(R.id.movie_producer)
    TextView movieProducer;

    @BindView(R.id.movie_crawl)
    TextView movieCrawlText;

    private String movieId;

    public static MovieDetailsFragment newInstance(String movieId) {
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_ID, movieId);
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_details_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getExtras();
        setUI();
    }

    private void getExtras() {
        movieId = getArguments().getString(KEY_MOVIE_ID);
        if (movieId == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            // TODO: 5.11.2017. go back 
        }
    }

    private void setUI() {

        MovieEndpoint service = BackendFactory.getMovieEndpoint();
        Call<SwapiMoviesResponse> call = service.getMovieById(movieId);
        call.enqueue(new Callback<SwapiMoviesResponse>() {
            @Override
            public void onResponse(Call<SwapiMoviesResponse> call, Response<SwapiMoviesResponse> response) {
                if (response.body().getCount() <= 1) {
                    Movie movie = response.body().getResults().get(0);
                    if (movie != null) {
                        showMovie(movie);
                    }
                }
            }

            @Override
            public void onFailure(Call<SwapiMoviesResponse> call, Throwable t) {
                // TODO: 5.11.2017. error and out
            }
        });
    }

    private void showMovie(Movie movie) {
        movieTitle.setText(movie.getTitle());
        movieEpisode.setText(String.valueOf(movie.getEpisodeId()));
        movieProducer.setText(movie.getProducer());
        movieCrawlText.setText(movie.getOpeningCrawl());
    }
}
