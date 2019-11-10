package com.jundana.moviecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.jundana.moviecatalogue.model.Tvshow;

import java.util.Objects;

import static com.jundana.moviecatalogue.adapter.TvshowsAdapter.DATA_TVSHOW_PARCELABLE;
import static com.jundana.moviecatalogue.helper.UtilsApi.PHOTO_URL;

public class TvshowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Tvshow tvshow = intent.getParcelableExtra(DATA_TVSHOW_PARCELABLE);
        ImageView imgPhoto = findViewById(R.id.imageView);
        TextView tvItemDetail = findViewById(R.id.tv_item_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Objects.requireNonNull(tvshow).getTvShowName());

        String photo = PHOTO_URL + tvshow.getPhoto();
        Glide.with(this).load(photo).into(imgPhoto);
        tvItemDetail.setText(tvshow.getTvShowDetail());
    }
}
