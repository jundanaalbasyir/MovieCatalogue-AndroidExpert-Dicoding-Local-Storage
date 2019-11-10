package com.jundana.moviecatalogue.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.fragment.MovieFragment;
import com.jundana.moviecatalogue.fragment.TvshowFragment;

public class VideoPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public VideoPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.movie,
            R.string.tvshow
    };

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFragment();
                break;
            case 1:
                fragment = new TvshowFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        return 2;
    }
}
