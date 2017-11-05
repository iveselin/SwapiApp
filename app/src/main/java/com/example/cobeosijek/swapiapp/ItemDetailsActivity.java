package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.category_list.CategoryTypeEnum;
import com.example.cobeosijek.swapiapp.details.MovieDetailsFragment;
import com.example.cobeosijek.swapiapp.models.Person;
import com.example.cobeosijek.swapiapp.response.SwapiPeopleResponse;
import com.example.cobeosijek.swapiapp.retrofit.BackendFactory;
import com.example.cobeosijek.swapiapp.retrofit.PeopleEndpoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO: 31/10/2017 Implement fragments for every type of item and display it accordingly


public class ItemDetailsActivity extends BaseActivity {

    private static final String KEY_ID_SEND = "item_Id";
    private static final String KEY_TYPE_SEND = "item_type";

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;



    @BindView(R.id.action_bar_heading)
    TextView layoutTitle;

    private String itemId;
    private String itemType;

    public static Intent getLaunchIntent(Context fromContext, String itemId, String itemType) {
        Intent launchIntent = new Intent(fromContext, ItemDetailsActivity.class);
        launchIntent.putExtra(KEY_ID_SEND, itemId);
        launchIntent.putExtra(KEY_TYPE_SEND, itemType);
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
        if (getIntent().hasExtra(KEY_ID_SEND) && getIntent().hasExtra(KEY_TYPE_SEND)) {
            itemId = getIntent().getStringExtra(KEY_ID_SEND);
            itemType = getIntent().getStringExtra(KEY_TYPE_SEND);
            if (itemId == null || itemId.isEmpty() || itemType == null || itemType.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);

        layoutTitle.setText(R.string.person_layout_title);
        if (itemType.equals(CategoryTypeEnum.FILMS.name())) {
            MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.newInstance(itemId);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, movieDetailsFragment);

            fragmentTransaction.commit();

        } else {
            PeopleEndpoint service = BackendFactory.getPeopleEndpoint();
            Call<SwapiPeopleResponse> call = service.getPerson(itemId);
            call.enqueue(new Callback<SwapiPeopleResponse>() {

                @Override
                public void onResponse(Call<SwapiPeopleResponse> call, Response<SwapiPeopleResponse> response) {
                    if (response.body().getCount() <= 1) {
                        Person person = response.body().getResults().get(0);
                        if (person != null) {
//                            showPerson(person);
                        }

                    }
                }

                @Override
                public void onFailure(Call<SwapiPeopleResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

//    private void showPerson(Person personToShow) {
//        itemTitle.setText(personToShow.getName());
//    }

    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }
}
