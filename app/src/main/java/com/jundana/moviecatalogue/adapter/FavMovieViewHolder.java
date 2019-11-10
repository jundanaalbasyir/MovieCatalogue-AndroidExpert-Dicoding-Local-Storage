package com.jundana.moviecatalogue.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jundana.moviecatalogue.R;


class FavMovieViewHolder extends RecyclerView.ViewHolder {
    TextView tvshowName, tvshowDetail;
    ImageView deleteFavMovie;

    FavMovieViewHolder(View itemView) {
        super(itemView);
        tvshowName = itemView.findViewById(R.id.tvshow_name);
        tvshowDetail = itemView.findViewById(R.id.tvshow_detail);
        deleteFavMovie = itemView.findViewById(R.id.delete_fav_tvshow);
    }
}
