package com.example.cobeosijek.swapiapp.item_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cobeosijek.swapiapp.R;
import com.example.cobeosijek.swapiapp.base.OnItemClickListener;
import com.example.cobeosijek.swapiapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cobeosijek on 30/10/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    private OnItemClickListener listener;

    public void setMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void addMovieList(List<Movie> itemList) {
        this.movieList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_item, parent, false);
        return new ItemViewHolder(itemView, listener);
    }

    public void setOnItemListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (movieList.isEmpty() || movieList.get(position) == null) {
            return;
        }

        Movie movie = movieList.get(position);

        holder.itemName.setText(movie.getTitle());
        holder.firstAttribute.setText(String.valueOf(movie.getEpisodeId()));
        holder.secondAttribute.setText(movie.getProducer());

        holder.setItemId(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
