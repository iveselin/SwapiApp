package com.example.cobeosijek.swapiapp.item_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.base.OnLastItemReachedListener;
import com.example.cobeosijek.swapiapp.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Person> personList = new ArrayList<>();
    private OnItemClickListener clickListener;
    private OnLastItemReachedListener lastItemReachedListener;


    public void setPersonList(List<Person> personList) {
        this.personList.clear();
        this.personList.addAll(personList);
        notifyDataSetChanged();
    }

    public void addPersonList(List<Person> itemList) {
        this.personList.addAll(itemList);
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
        if (personList.isEmpty() || personList.get(position) == null) {
            return;
        }

        Person person = personList.get(position);

        Context holderContext = holder.itemView.getContext();
        holder.itemName.setText(person.getName());
        holder.firstAttribute.setText(String.format(holderContext.getString(R.string.birth_year_format), person.getBirthYear()));
        holder.secondAttribute.setVisibility(View.GONE);
        holder.setItemId(person.getName());

        handleItemPosition(position);

        if (position % 2 == 1) {
            holder.setGray();
        }else {
            holder.setWhite();
        }
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
        return personList.size();
    }
}
