package com.jundana.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.adapter.TvshowsAdapter;
import com.jundana.moviecatalogue.helper.BaseApiService;
import com.jundana.moviecatalogue.helper.UtilsApi;
import com.jundana.moviecatalogue.model.Tvshow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jundana.moviecatalogue.adapter.TvshowsAdapter.DATA_TVSHOW_PARCELABLE;
import static com.jundana.moviecatalogue.helper.UtilsApi.api_key;

public class TvshowFragment extends Fragment {
    private RecyclerView rvTvshow;
    private BaseApiService mApiService;

    private ArrayList<Tvshow> list;
    private TvshowsAdapter listTvshowAdapter;

    private ProgressBar progressBar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        mApiService = UtilsApi.getAPIService();
        rvTvshow = view.findViewById(R.id.rv_tvshow);
        progressBar = view.findViewById(R.id.spin_kit);

        if (savedInstanceState != null) {
            progressBar.setVisibility(View.INVISIBLE);
            list = new ArrayList<>();
            list = savedInstanceState.getParcelableArrayList(DATA_TVSHOW_PARCELABLE);
            rvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
            listTvshowAdapter = new TvshowsAdapter(getContext(), list);
            rvTvshow.setAdapter(listTvshowAdapter);
        } else {
            list = new ArrayList<>();
            showRecyclerList();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(DATA_TVSHOW_PARCELABLE, list);
        super.onSaveInstanceState(outState);
    }

    private void showRecyclerList() {
        Sprite wanderingCubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(wanderingCubes);
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getTvShowRequest(api_key).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("results"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject myTvshow = jsonArray.getJSONObject(i);
                        Tvshow tvshow = new Tvshow();
                        tvshow.setId(myTvshow.getInt("id"));
                        tvshow.setPhoto(myTvshow.getString("poster_path"));
                        tvshow.setTvShowName(myTvshow.getString("original_name"));
                        tvshow.setTvShowDetail(myTvshow.getString("overview"));
                        list.add(tvshow);
                    }
                    rvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
                    listTvshowAdapter = new TvshowsAdapter(getContext(), list);
                    rvTvshow.setHasFixedSize(true);
                    rvTvshow.setAdapter(listTvshowAdapter);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Tidak Ada Internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}
