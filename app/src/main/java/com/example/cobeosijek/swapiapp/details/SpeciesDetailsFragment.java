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
import com.example.cobeosijek.swapiapp.models.Species;
import com.example.cobeosijek.swapiapp.response.SwapiResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.SpeciesEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobeosijek on 06/11/2017.
 */

public class SpeciesDetailsFragment extends Fragment {

    private static final String KEY_SPECIES_ID = "specie_id";

    @BindView(R.id.specie_name)
    TextView name;

    @BindView(R.id.specie_classification)
    TextView classificaton;

    @BindView(R.id.specie_language)
    TextView language;

    @BindView(R.id.specie_lifespan)
    TextView averageLifespan;

    private String specieId;

    public static SpeciesDetailsFragment newInstance(String specieId) {
        Bundle args = new Bundle();
        args.putString(KEY_SPECIES_ID, specieId);
        SpeciesDetailsFragment fragment = new SpeciesDetailsFragment();
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
        return inflater.inflate(R.layout.species_details_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setUi();
    }

    private void getExtras() {
        specieId = getArguments().getString(KEY_SPECIES_ID);
        if (specieId == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private void setUi() {

        SpeciesEndpoint service = BackendFactory.getSpeciesEndpoint();
        Call<SwapiResponse<Species>> call = service.getSpecie(specieId);
        call.enqueue(new Callback<SwapiResponse<Species>>() {

            @Override
            public void onResponse(Call<SwapiResponse<Species>> call, Response<SwapiResponse<Species>> response) {
                if (response.body().getCount() <= 1) {
                    Species species = response.body().getResults().get(0);
                    if (species != null) {
                        showSpecies(species);
                    }
                }
            }

            @Override
            public void onFailure(Call<SwapiResponse<Species>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }

    private void showSpecies(Species species) {
        name.setText(species.getName());
        language.setText(String.format(getString(R.string.language_format), species.getLanguage()));
        classificaton.setText(String.format(getString(R.string.classification_format), species.getClassification()));
        averageLifespan.setText(String.format(getString(R.string.lifespan_format), species.getAverageLifespan()));
    }

}
