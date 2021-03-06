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
import com.example.cobeosijek.swapiapp.models.Starship;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.StarshipsEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobeosijek on 06/11/2017.
 */

public class StarshipDetailsFragment extends Fragment {

    private static final String KEY_STARSHIP_ID = "starship_id";

    @BindView(R.id.starship_name)
    TextView starshipName;

    @BindView(R.id.starship_class)
    TextView starshipClass;

    @BindView(R.id.starship_model)
    TextView starshipModel;

    @BindView(R.id.starship_crew)
    TextView starshipCrew;

    @BindView(R.id.starship_passengers)
    TextView starshipPassengers;

    private String starshipId;

    public static StarshipDetailsFragment newInstance(String starshipId) {
        Bundle args = new Bundle();
        args.putString(KEY_STARSHIP_ID, starshipId);
        StarshipDetailsFragment fragment = new StarshipDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtras();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.starship_details_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setUi();
    }

    private void getExtras() {
        starshipId = getArguments().getString(KEY_STARSHIP_ID);
        if (starshipId == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private void setUi() {

        StarshipsEndpoint service = BackendFactory.getStarshipsEndpoint();
        Call<SwapiResponse<Starship>> call = service.getStarship(starshipId);
        call.enqueue(new Callback<SwapiResponse<Starship>>() {
            @Override
            public void onResponse(Call<SwapiResponse<Starship>> call, Response<SwapiResponse<Starship>> response) {
                if (response.body().getCount() <= 1) {
                    Starship starship = response.body().getResults().get(0);
                    if (starship != null) {
                        showStarship(starship);
                    }
                }
            }

            @Override
            public void onFailure(Call<SwapiResponse<Starship>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }

    private void showStarship(Starship starship) {
        starshipName.setText(starship.getName());
        starshipClass.setText(String.format(getString(R.string.class_format), starship.getStarshipClass()));
        starshipModel.setText(String.format(getString(R.string.model_format), starship.getModel()));
        starshipCrew.setText(String.format(getString(R.string.crew_format), starship.getCrew()));
        starshipPassengers.setText(String.format(getString(R.string.passenger_format), starship.getPassengers()));
    }
}
