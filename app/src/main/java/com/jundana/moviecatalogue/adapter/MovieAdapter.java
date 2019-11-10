package com.jundana.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jundana.moviecatalogue.MovieDetailActivity;
import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.model.Movie;
import com.jundana.moviecatalogue.FavMovieActivity;

import java.util.ArrayList;
import java.util.List;

import static com.jundana.moviecatalogue.helper.UtilsApi.PHOTO_URL;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {
    public static final String DATA_MOVIE_PARCELABLE = "data_movie";

    private Context mCtx;
    private List<Movie> listMovie;

    public MovieAdapter(Context context, ArrayList<Movie> list) {
        this.mCtx = context;
        this.listMovie = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder listViewHolder, final int position) {
        final Movie movies = listMovie.get(position);
        final String movieName = listMovie.get(position).getMovieName();
        final String movieDetail = listMovie.get(position).getMovieDetail();
        Glide.with(listViewHolder.itemView.getContext())
                .load(PHOTO_URL + movies.getPhoto())
                .apply(new RequestOptions())
                .into(listViewHolder.imgPhoto);
        listViewHolder.tvItemName.setText(movieName);
        listViewHolder.tvItemDetail.setText(movieDetail);
        listViewHolder.Overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int position = listViewHolder.getAdapterPosition();
                        Movie movie = listMovie.get(position);
                        movie.setId(movie.getId());
                        movie.setMovieName(movie.getMovieName());
                        movie.setMovieDetail(movie.getMovieDetail());
                        Intent intent = new Intent(mCtx, FavMovieActivity.class);
                        intent.putExtra(DATA_MOVIE_PARCELABLE, movie);
                        Toast.makeText(mCtx, "Added to Favorite Movie!", Toast.LENGTH_SHORT).show();
                        mCtx.startActivity(intent);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        ImageButton Overflow;
        TextView tvItemName, tvItemDetail;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
            tvItemDetail = itemView.findViewById(R.id.tv_item_detail);
            Overflow = itemView.findViewById(R.id.overflow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = listMovie.get(position);
            movie.setId(movie.getId());
            movie.setMovieName(movie.getMovieName());
            movie.setMovieDetail(movie.getMovieDetail());
            Intent intent = new Intent(mCtx, MovieDetailActivity.class);
            intent.putExtra(DATA_MOVIE_PARCELABLE, movie);
            mCtx.startActivity(intent);
        }
    }
}
