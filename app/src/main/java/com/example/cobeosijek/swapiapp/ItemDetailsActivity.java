package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.response.SwapiPeopleResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.PeopleEndpoint;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsActivity extends BaseActivity {

    private static final String KEY_ID_SEND = "item_Id";


    private String itemId;

    public static Intent getLaunchIntent(Context fromContext, String itemId) {
        Intent launchIntent = new Intent(fromContext, ItemDetailsActivity.class);
        launchIntent.putExtra(KEY_ID_SEND, itemId);
        return launchIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getExtras();
        setUI();
    }

    private void getExtras() {
        if (getIntent().hasCategory(KEY_ID_SEND)) {
            itemId = getIntent().getStringExtra(KEY_ID_SEND);
            if (itemId == null || itemId.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    @Override
    public void setUI() {
        ButterKnife.bind(this);

        PeopleEndpoint service = BackendFactory.getRetrofitInstance().create(PeopleEndpoint.class);
        retrofit2.Call<SwapiPeopleResponse> call = service.getPerson(itemId);
        call.enqueue(new Callback<SwapiPeopleResponse>() {
            @Override
            public void onResponse(Call<SwapiPeopleResponse> call, Response<SwapiPeopleResponse> response) {
                showPerson(response);
            }

            @Override
            public void onFailure(Call<SwapiPeopleResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void showPerson(Response<SwapiPeopleResponse> response) {
        Toast.makeText(this, "response count: " + response.body().getCount(), Toast.LENGTH_SHORT).show();
        if (response.body().getCount() <= 1) {
            Toast.makeText(getApplicationContext(), "Sucess, you got" + response.body().getResults().get(0).getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
