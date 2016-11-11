package com.nickyluis.nplay_ijkplayer.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;

import com.nickyluis.nplay_ijkplayer.fragment.BlankFragment;

/**
 * Created by Nicky on 2016/11/12.
 * m
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return BlankFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
