package com.example.cobeosijek.swapiapp.item_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.models.Species;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4.11.2017..
 */

public class SpeciesAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Species> speciesList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnLastItemReachedListener lastItemReachedListener;


    public void setSpeciesList(List<Species> speciesList) {
        this.speciesList.clear();
        this.speciesList.addAll(speciesList);
        notifyDataSetChanged();
    }

    public void addSpeciesList(List<Species> itemList) {
        this.speciesList.addAll(itemList);
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
        if (speciesList.isEmpty() || speciesList.get(position) == null) {
            return;
        }

        Species species = speciesList.get(position);

        Context holderContext = holder.itemView.getContext();
        holder.itemName.setText(species.getName());
        holder.firstAttribute.setText(String.format(holderContext.getString(R.string.language_format), species.getLanguage()));
        holder.secondAttribute.setVisibility(View.GONE);
        holder.setItemId(species.getName());

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
        return speciesList.size();
    }
}
