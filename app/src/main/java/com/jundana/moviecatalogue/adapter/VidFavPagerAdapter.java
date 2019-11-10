package com.jundana.moviecatalogue.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jundana.moviecatalogue.R;
import com.jundana.moviecatalogue.fragment.MovieFavFragment;
import com.jundana.moviecatalogue.fragment.TvshowFavFragment;

public class VidFavPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public VidFavPagerAdapter(Context context, FragmentManager fm) {
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
                fragment = new MovieFavFragment();
                break;
            case 1:
                fragment = new TvshowFavFragment();
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
