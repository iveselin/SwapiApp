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
import com.example.cobeosijek.swapiapp.models.Vehicle;
import com.example.cobeosijek.swapiapp.response.SwapiVehicleResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.VehiclesEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cobeosijek on 06/11/2017.
 */

public class VehicleDetailsFragment extends Fragment {

    private static final String KEY_VEHICLE_ID = "vehicle_id";

    @BindView(R.id.vehicle_name)
    TextView vehicleName;

    @BindView(R.id.vehicle_model)
    TextView vehicleModel;

    @BindView(R.id.vehicle_class)
    TextView vehicleClass;

    @BindView(R.id.vehicle_crew)
    TextView vehicleCrew;

    @BindView(R.id.vehicle_passengers)
    TextView vehiclePassengers;

    private String vehicleId;

    public static VehicleDetailsFragment newInstance(String vehicleId) {
        Bundle args = new Bundle();
        args.putString(KEY_VEHICLE_ID, vehicleId);
        VehicleDetailsFragment fragment = new VehicleDetailsFragment();
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
        return inflater.inflate(R.layout.vehicle_details_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        setUi();
    }

    private void getExtras() {
        vehicleId = getArguments().getString(KEY_VEHICLE_ID);
        if (vehicleId == null) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private void setUi() {

        VehiclesEndpoint service = BackendFactory.getVehiclesEndpoint();
        Call<SwapiVehicleResponse> call = service.getVehicle(vehicleId);
        call.enqueue(new Callback<SwapiVehicleResponse>() {
            @Override
            public void onResponse(Call<SwapiVehicleResponse> call, Response<SwapiVehicleResponse> response) {
                if (response.body().getCount() <= 1) {
                    Vehicle vehicle = response.body().getResults().get(0);
                    if (vehicle != null) {
                        showVehicle(vehicle);
                    }
                }
            }

            @Override
            public void onFailure(Call<SwapiVehicleResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), R.string.api_fail_text, Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }

    private void showVehicle(Vehicle vehicle) {
        vehicleName.setText(vehicle.getName());
        vehicleClass.setText(String.format(getString(R.string.class_format), vehicle.getVehicleClass()));
        vehicleModel.setText(String.format(getString(R.string.model_format), vehicle.getModel()));
        vehicleCrew.setText(String.format(getString(R.string.crew_format), vehicle.getCrew()));
        vehiclePassengers.setText(String.format(getString(R.string.passenger_format), vehicle.getPassengers()));
    }

}
