package com.example.cobeosijek.swapiapp.category_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.swapiapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private String itemId;

    @BindView(R.id.category_name)
    TextView categoryName;

    public CategoryViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);


        ButterKnife.bind(this, itemView);
        this.listener = listener;
    }

    @OnClick
    void onItemClicked() {
        if (listener != null) {
            listener.onItemClick(itemId);
        }
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setGray() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorLightGrey));
    }
}
