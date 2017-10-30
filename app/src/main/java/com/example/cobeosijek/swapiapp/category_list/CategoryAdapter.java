package com.example.cobeosijek.swapiapp.category_list;

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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categoryList = new ArrayList<>();
    private OnItemClickListener listener;

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList.clear();
        this.categoryList.addAll(categoryList);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View categoryView = inflater.inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryView, listener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        if (categoryList.isEmpty() || categoryList.get(position) == null) {
            return;
        }

        if (position % 2 != 0) {
            holder.setGray();
        }

        Category category = categoryList.get(position);

        holder.setItemId(category.getCategoryURL());
        holder.categoryName.setText(category.getCategoryName());
        holder.setItemId(category.getCategoryType().name());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
