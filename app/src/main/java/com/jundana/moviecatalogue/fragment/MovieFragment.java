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
import com.jundana.moviecatalogue.adapter.MovieAdapter;
import com.jundana.moviecatalogue.helper.BaseApiService;
import com.jundana.moviecatalogue.helper.UtilsApi;
import com.jundana.moviecatalogue.model.Movie;

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

import static com.jundana.moviecatalogue.adapter.MovieAdapter.DATA_MOVIE_PARCELABLE;
import static com.jundana.moviecatalogue.helper.UtilsApi.api_key;

public class MovieFragment extends Fragment {
    private RecyclerView rvMovies;
    private BaseApiService mApiService;

    private ArrayList<Movie> list;
    private MovieAdapter listmovieAdapter;
    private ProgressBar progressBar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        mApiService = UtilsApi.getAPIService();
        rvMovies = view.findViewById(R.id.rv_movie);
        progressBar = view.findViewById(R.id.spin_kit);

        if (savedInstanceState != null) {
            progressBar.setVisibility(View.INVISIBLE);
            list = new ArrayList<>();
            list = savedInstanceState.getParcelableArrayList(DATA_MOVIE_PARCELABLE);
            rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            listmovieAdapter = new MovieAdapter(getContext(), list);
            rvMovies.setAdapter(listmovieAdapter);
        } else {
            list = new ArrayList<>();
            showRecyclerList();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(DATA_MOVIE_PARCELABLE, list);
        super.onSaveInstanceState(outState);
    }

    private void showRecyclerList() {
        Sprite wanderingCubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(wanderingCubes);
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getMovieRequest(api_key).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("results"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject myMovie = jsonArray.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setId(myMovie.getInt("id"));
                        movie.setPhoto(myMovie.getString("poster_path"));
                        movie.setMovieName(myMovie.getString("original_title"));
                        movie.setMovieDetail(myMovie.getString("overview"));
                        list.add(movie);
                    }
                    rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
                    listmovieAdapter = new MovieAdapter(getContext(), list);
                    rvMovies.setHasFixedSize(true);
                    rvMovies.setAdapter(listmovieAdapter);
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
