package com.example.cobeosijek.swapiapp.item_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 4.11.2017..
 */

public class VehiclesAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<Vehicle> vehicleList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnLastItemReachedListener lastItemReachedListener;


    public void setVehicleList(List<Vehicle> starshipsList) {
        this.vehicleList.clear();
        this.vehicleList.addAll(starshipsList);
        notifyDataSetChanged();
    }

    public void addVehiclesList(List<Vehicle> itemList) {
        this.vehicleList.addAll(itemList);
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
        if (vehicleList.isEmpty() || vehicleList.get(position) == null) {
            return;
        }

        Vehicle vehicle = vehicleList.get(position);

        Context holderContext = holder.itemView.getContext();
        holder.itemName.setText(vehicle.getName());
        holder.firstAttribute.setText(String.format(holderContext.getString(R.string.model_format), vehicle.getModel()));
        holder.secondAttribute.setVisibility(View.GONE);
        holder.setItemId(vehicle.getName());

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
        return vehicleList.size();
    }
}
