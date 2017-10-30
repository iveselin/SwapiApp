package com.example.cobeosijek.swapiapp.item_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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



    @OnClick
    void onItemClicked() {
        if (listener != null) {
            listener.onItemClick(itemId);
        }
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
