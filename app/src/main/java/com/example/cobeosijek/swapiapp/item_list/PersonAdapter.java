package com.example.cobeosijek.swapiapp.item_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Person> personList = new ArrayList<>();
    private OnItemClickListener listener;

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
        return new ItemViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (personList.isEmpty() || personList.get(position) == null) {
            return;
        }

        Person person = personList.get(position);

        holder.itemName.setText(person.getName());
        holder.firstAttribute.setText(person.getBirthYear());
        holder.setItemId(person.getName());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
