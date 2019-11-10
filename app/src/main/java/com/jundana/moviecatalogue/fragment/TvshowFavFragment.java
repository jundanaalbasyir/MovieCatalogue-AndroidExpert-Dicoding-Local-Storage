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
import com.jundana.moviecatalogue.model.Tvshow;
import com.jundana.moviecatalogue.adapter.FavTvshowAdapter;
import com.jundana.moviecatalogue.helper.SqliteFavTvshowDatabase;
import com.jundana.moviecatalogue.model.TvshowFav;

import java.util.ArrayList;
import java.util.Objects;

import static android.content.ContentValues.TAG;
import static com.jundana.moviecatalogue.adapter.TvshowsAdapter.DATA_TVSHOW_PARCELABLE;

public class TvshowFavFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tvshow_fav, container, false);

        RecyclerView favTvshowView = view.findViewById(R.id.product_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        favTvshowView.setLayoutManager(linearLayoutManager);
        favTvshowView.setHasFixedSize(true);
        SqliteFavTvshowDatabase mDatabase = new SqliteFavTvshowDatabase(getContext());
        ArrayList<TvshowFav> allFavTvshow = mDatabase.listFavTvshow();

        if (allFavTvshow.size() > 0) {
            favTvshowView.setVisibility(View.VISIBLE);
            FavTvshowAdapter mAdapter = new FavTvshowAdapter(getContext(), allFavTvshow);
            favTvshowView.setAdapter(mAdapter);

        } else {
            favTvshowView.setVisibility(View.GONE);
        }

        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        Tvshow tvshow = intent.getParcelableExtra(DATA_TVSHOW_PARCELABLE);
        if (tvshow == null) {
            Log.d(TAG, "onCreateView: Welcome");
        } else {
            final int id = Objects.requireNonNull(tvshow).getId();
            final String tvshowName = tvshow.getTvShowName();
            final String tvshowDetail = tvshow.getTvShowDetail();

            if (TextUtils.isEmpty(tvshowName)) {
                Toast.makeText(getContext(), "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
            } else {
                TvshowFav newFavTvshow = new TvshowFav(id, tvshowName, tvshowDetail);
                mDatabase.addFavTvshow(newFavTvshow);
                getActivity().finish();
            }
        }
        return view;
    }
}
