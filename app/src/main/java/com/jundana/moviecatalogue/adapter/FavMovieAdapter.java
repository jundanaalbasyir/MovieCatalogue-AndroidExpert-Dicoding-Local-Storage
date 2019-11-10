package com.jundana.moviecatalogue.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.helper.SqliteFavMovieDatabase;
import com.jundana.moviecatalogue.model.MovieFav;

import java.util.ArrayList;

public class FavMovieAdapter extends RecyclerView.Adapter<FavMovieViewHolder> {

    private Context context;
    private ArrayList<MovieFav> listMovies;

    private SqliteFavMovieDatabase mDatabase;

    public FavMovieAdapter(Context context, ArrayList<MovieFav> listMovies) {
        this.context = context;
        this.listMovies = listMovies;
        mDatabase = new SqliteFavMovieDatabase(context);
    }

    @NonNull
    @Override
    public FavMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_movie, parent, false);
        return new FavMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavMovieViewHolder holder, int position) {
        final MovieFav movieFav = listMovies.get(position);

        holder.tvshowName.setText(movieFav.getMovieName());
        holder.tvshowDetail.setText(movieFav.getMovieDetail());

        holder.deleteFavMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.deleteFavoriteMovie(movieFav.getId());
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }
}
