package com.jundana.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.jundana.moviecatalogue.adapter.VidFavPagerAdapter;

import java.util.Objects;

public class FavMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie);

        VidFavPagerAdapter sectionsPagerAdapter = new VidFavPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#000000"));
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
    }
}
