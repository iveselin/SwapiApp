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
import com.example.cobeosijek.swapiapp.models.Person;
import com.example.cobeosijek.swapiapp.response.SwapiPeopleResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.PeopleEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobeosijek on 06/11/2017.
 */

public class PeopleDetailsFragment extends Fragment {

    private static final String KEY_PERSON_ID = "person_id";

    @BindView(R.id.person_name)
    TextView personName;

    @BindView(R.id.person_birth_year)
    TextView personBirthYear;

    @BindView(R.id.person_gender)
    TextView personGender;

    @BindView(R.id.person_mass)
    TextView personMass;

    @BindView(R.id.person_height)
    TextView personHeight;

    private String personId;


    public static PeopleDetailsFragment newInstance(String personId) {
        Bundle args = new Bundle();
        args.putString(KEY_PERSON_ID, personId);
        PeopleDetailsFragment fragment = new PeopleDetailsFragment();
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
        return inflater.inflate(R.layout.person_details_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getExtras();
        setUi();
    }

    private void getExtras() {
        personId = getArguments().getString(KEY_PERSON_ID);
        if (personId == null) {
            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private void setUi() {
        PeopleEndpoint service = BackendFactory.getPeopleEndpoint();
        Call<SwapiPeopleResponse> call = service.getPerson(personId);
        call.enqueue(new Callback<SwapiPeopleResponse>() {

            @Override
            public void onResponse(Call<SwapiPeopleResponse> call, Response<SwapiPeopleResponse> response) {
                if (response.body().getCount() <= 1) {
                    Person person = response.body().getResults().get(0);
                    if (person != null) {
                        showPerson(person);
                    }

                }
            }

            @Override
            public void onFailure(Call<SwapiPeopleResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
    }

    private void showPerson(Person person) {
        personName.setText(person.getName());
        personBirthYear.setText(String.format(getString(R.string.birth_year_format), person.getBirthYear()));
        personGender.setText(String.format(getString(R.string.gender_format), person.getGender()));
        personMass.setText(String.format(getString(R.string.person_mass_format), person.getMass()));
        personHeight.setText(String.format(getString(R.string.person_height_format), person.getHeight()));
    }
}
