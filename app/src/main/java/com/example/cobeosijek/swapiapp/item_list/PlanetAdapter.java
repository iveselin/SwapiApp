package com.example.cobeosijek.swapiapp.item_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.models.Planet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 31/10/2017.
 */

public class PlanetAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Planet> planetList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnLastItemReachedListener lastItemReachedListener;


    public void setPlanetList(List<Planet> planetList) {
        this.planetList.clear();
        this.planetList.addAll(planetList);
        notifyDataSetChanged();
    }

    public void addPlanetList(List<Planet> itemList) {
        this.planetList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_item, parent, false);
        return new ItemViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (planetList.isEmpty() || planetList.get(position) == null) {
            return;
        }

        Planet planet = planetList.get(position);

        holder.itemName.setText(planet.getName());
        holder.firstAttribute.setText(planet.getPopulation());
        holder.setItemId(planet.getName());

        handleItemPosition(position);


    }

    private void handleItemPosition(int position) {
        if (getItemCount() >= 10 && position == getItemCount() - 1 && position > 0) {
            lastItemReachedListener.onLastItem();
        }
    }

    public void setLastItemReachedListener(OnLastItemReachedListener lastItemReachedListener) {
        this.lastItemReachedListener = lastItemReachedListener;
    }

    public void setOnItemListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public int getItemCount() {
        return planetList.size();
    }
}
