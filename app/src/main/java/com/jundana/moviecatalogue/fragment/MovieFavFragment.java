package com.jundana.moviecatalogue.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.model.Movie;
import com.jundana.moviecatalogue.adapter.FavMovieAdapter;
import com.jundana.moviecatalogue.helper.SqliteFavMovieDatabase;
import com.jundana.moviecatalogue.model.MovieFav;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static com.jundana.moviecatalogue.adapter.MovieAdapter.DATA_MOVIE_PARCELABLE;

public class MovieFavFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_fav, container, false);

        RecyclerView favMovieView = view.findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        favMovieView.setLayoutManager(linearLayoutManager);
        favMovieView.setHasFixedSize(true);
        SqliteFavMovieDatabase mDatabase = new SqliteFavMovieDatabase(getContext());
        ArrayList<MovieFav> allFavMovie = mDatabase.listFavMovie();

        if (allFavMovie.size() > 0) {
            favMovieView.setVisibility(View.VISIBLE);
            FavMovieAdapter mAdapter = new FavMovieAdapter(getContext(), allFavMovie);
            favMovieView.setAdapter(mAdapter);

        } else {
            favMovieView.setVisibility(View.GONE);
        }

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        Movie movie = intent.getParcelableExtra(DATA_MOVIE_PARCELABLE);
        if (movie == null) {
            Log.d(TAG, "onCreateView: Welcome");
        } else {
            final int id = Objects.requireNonNull(movie).getId();
            final String movieName = movie.getMovieName();
            final String movieDetail = movie.getMovieDetail();

            if (TextUtils.isEmpty(movieName)) {
                Toast.makeText(getContext(), "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
            } else {
                MovieFav newFavMovie = new MovieFav(id, movieName, movieDetail);
                mDatabase.addFavMovie(newFavMovie);
                getActivity().finish();
            }
        }
        return view;
    }
}
