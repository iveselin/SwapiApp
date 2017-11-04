package com.example.cobeosijek.swapiapp.item_list;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.models.Species;
import com.example.cobeosijek.swapiapp.models.Starship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4.11.2017..
 */

public class StarshipsAdapter extends RecyclerView.Adapter<ItemViewHolder>{
    private List<Starship> starshipList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnLastItemReachedListener lastItemReachedListener;


    public void setStarshipList(List<Starship> starshipsList) {
        this.starshipList.clear();
        this.starshipList.addAll(starshipsList);
        notifyDataSetChanged();
    }

    public void addStarshipsList(List<Starship> itemList) {
        this.starshipList.addAll(itemList);
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
        if (starshipList.isEmpty() || starshipList.get(position) == null) {
            return;
        }

        Starship starship = starshipList.get(position);

        holder.itemName.setText(starship.getName());
        holder.firstAttribute.setText(starship.getModel());
        holder.setItemId(starship.getName());

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
        return starshipList.size();
    }
}
