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
import com.example.cobeosijek.swapiapp.models.Planet;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.PlanetEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobeosijek on 06/11/2017.
 */

public class PlanetDetailsFragment extends Fragment {

    private static final String KEY_PLANET_ID = "planet_id";

    @BindView(R.id.planet_name)
    TextView name;

    @BindView(R.id.planet_population)
    TextView population;

    @BindView(R.id.planet_orbital_period)
    TextView orbitalPeriod;

    @BindView(R.id.planet_rotation_period)
    TextView rotationPeriod;

    @BindView(R.id.planet_terrain)
    TextView terrain;

    private String planetId;

    public static PlanetDetailsFragment newInstance(String planetId) {
        Bundle args = new Bundle();
        args.putString(KEY_PLANET_ID, planetId);
        PlanetDetailsFragment fragment = new PlanetDetailsFragment();
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
        return inflater.inflate(R.layout.planet_details_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setUi();
    }

    private void getExtras() {
        planetId = getArguments().getString(KEY_PLANET_ID);
        if (planetId == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }


    private void setUi() {

        PlanetEndpoint service = BackendFactory.getPlanetEndpoint();
        Call<SwapiResponse<Planet>> call = service.getPlanet(planetId);
        call.enqueue(new Callback<SwapiResponse<Planet>>() {
            @Override
            public void onResponse(Call<SwapiResponse<Planet>> call, Response<SwapiResponse<Planet>> response) {
                if (response.body().getCount() <= 1) {
                    Planet planet = response.body().getResults().get(0);
                    if (planet != null) {
                        showPlanet(planet);
                    }
                }
            }

            @Override
            public void onFailure(Call<SwapiResponse<Planet>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }

    private void showPlanet(Planet planet) {
        name.setText(planet.getName());
        population.setText(String.format(getString(R.string.population_format), planet.getPopulation()));
        orbitalPeriod.setText(String.format(getString(R.string.orbital_period_format), planet.getOrbitalPeriod()));
        rotationPeriod.setText(String.format(getString(R.string.rotation_period_format), planet.getRotationPeriod()));
        terrain.setText(String.format(getString(R.string.terrain_format), planet.getTerrain()));
    }
}
