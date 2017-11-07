package com.example.cobeosijek.swapiapp.item_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cobeosijek on 27/10/2017.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener listener;
    private String itemId;

    @BindView(R.id.item_name)
    TextView itemName;

    @BindView(R.id.item_first_attribute)
    TextView firstAttribute;

    @BindView(R.id.item_second_attribute)
    TextView secondAttribute;

    public ItemViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        this.listener = listener;
    }


    @OnClick(R.id.root_view)
    void onItemClicked() {
        if (listener != null) {
            listener.onItemClick(itemId);
        }
    }

    public void setGray() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorLightGrey));
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setWhite() {
        itemView.setBackgroundColor(itemView.getResources().getColor(R.color.colorWhite));
    }
}
