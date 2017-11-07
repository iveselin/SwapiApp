package com.example.cobeosijek.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobeosijek.swapiapp.base.BaseActivity;
import com.example.cobeosijek.swapiapp.category_list.CategoryTypeEnum;
import com.example.cobeosijek.swapiapp.details.MovieDetailsFragment;
import com.example.cobeosijek.swapiapp.details.PeopleDetailsFragment;
import com.example.cobeosijek.swapiapp.details.PlanetDetailsFragment;
import com.example.cobeosijek.swapiapp.details.SpeciesDetailsFragment;
import com.example.cobeosijek.swapiapp.details.StarshipDetailsFragment;
import com.example.cobeosijek.swapiapp.details.VehicleDetailsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ItemDetailsActivity extends BaseActivity {

    private static final String KEY_ID_SEND = "item_Id";
    private static final String KEY_TYPE_SEND = "item_type";

    @BindView(R.id.action_bar_back_icon)
    ImageView backIcon;

    @BindView(R.id.action_bar_heading)
    TextView layoutTitle;

    private String itemId;
    private CategoryTypeEnum itemType;

    public static Intent getLaunchIntent(Context fromContext, String itemId, CategoryTypeEnum itemType) {
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
            itemType = (CategoryTypeEnum) getIntent().getSerializableExtra(KEY_TYPE_SEND);

            if (itemId == null || itemId.isEmpty() || itemType == null) {
                Toast.makeText(getApplicationContext(), getString(R.string.default_error_message), Toast.LENGTH_SHORT).show();
                finish();
            }

        } else {
            finish();
        }
    }

    @Override
    public void setUI() {
        ButterKnife.bind(this);

        String title = "";

        Fragment fragment = null;

        switch (itemType) {
            case FILMS:
                fragment = MovieDetailsFragment.newInstance(itemId);
                title = getString(R.string.movie_details_title);
                break;

            case PEOPLE:
                fragment = PeopleDetailsFragment.newInstance(itemId);
                title = getString(R.string.person_details_title);
                break;

            case PLANETS:
                fragment = PlanetDetailsFragment.newInstance(itemId);
                title = getString(R.string.planet_details_title);
                break;

            case SPECIES:
                fragment = SpeciesDetailsFragment.newInstance(itemId);
                title = getString(R.string.specie_details_title);
                break;

            case STARSHIPS:
                fragment = StarshipDetailsFragment.newInstance(itemId);
                title = getString(R.string.starship_details_title);
                break;

            case VEHICLES:
                fragment = VehicleDetailsFragment.newInstance(itemId);
                title = getString(R.string.vehicle_details_title);
                break;

            default:
                Toast.makeText(getApplicationContext(), getString(R.string.default_error_message), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        layoutTitle.setText(title);
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


    @OnClick(R.id.action_bar_back_icon)
    void goBack() {
        onBackPressed();
    }
}
