package com.jundana.moviecatalogue.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.helper.SqliteFavTvshowDatabase;
import com.jundana.moviecatalogue.model.TvshowFav;

import java.util.ArrayList;

public class FavTvshowAdapter extends RecyclerView.Adapter<FavTvshowViewHolder> {

    private Context context;
    private ArrayList<TvshowFav> listTvshow;

    private SqliteFavTvshowDatabase mDatabase;

    public FavTvshowAdapter(Context context, ArrayList<TvshowFav> listTvshow) {
        this.context = context;
        this.listTvshow = listTvshow;
        mDatabase = new SqliteFavTvshowDatabase(context);
    }

    @NonNull
    @Override
    public FavTvshowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_tvshow, parent, false);
        return new FavTvshowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavTvshowViewHolder holder, int position) {
        final TvshowFav tvshowFav = listTvshow.get(position);

        holder.tvshowName.setText(tvshowFav.getTvshowName());
        holder.tvshowDetail.setText(tvshowFav.getTvshowDetail());

        holder.deleteFavTvshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteFavoriteTvshow(tvshowFav.getId());
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTvshow.size();
    }
}
