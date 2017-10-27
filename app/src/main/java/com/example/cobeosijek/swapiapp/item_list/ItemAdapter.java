package com.example.cobeosijek.swapiapp.item_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {


    private List<Category> itemList = new ArrayList<>();
    private OnItemClickListener listener;

    public void setItemList(/*List<Item> itemList*/) {
        this.itemList.clear();
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_item, parent, false);
        return new ItemViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (itemList.isEmpty() || itemList.get(position) == null) {
            return;
        }

//        Item item = itemList.get(position);

//        holder.itemName.setText();
//        holder.firstAttribute.setText();
//        holder.secondAttribute.setText();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
