package com.jundana.moviecatalogue.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jundana.moviecatalogue.R;

class FavTvshowViewHolder extends RecyclerView.ViewHolder {
    TextView tvshowName, tvshowDetail;
    ImageView deleteFavTvshow;

    FavTvshowViewHolder(View itemView) {
        super(itemView);
        tvshowName = itemView.findViewById(R.id.tvshow_name);
        tvshowDetail = itemView.findViewById(R.id.tvshow_detail);
        deleteFavTvshow = itemView.findViewById(R.id.delete_fav_tvshow);
    }
}
