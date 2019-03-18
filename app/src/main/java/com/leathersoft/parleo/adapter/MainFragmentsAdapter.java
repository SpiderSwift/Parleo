package com.leathersoft.parleo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.leathersoft.parleo.fragment.EventFragment;
import com.leathersoft.parleo.fragment.DialogFragment;

public class MainFragmentsAdapter extends FragmentStatePagerAdapter {

    private static final int EVENTS_INDEX = 0;
    private static final int NUM_OF_FRAGMENTS = 5;

    public MainFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case EVENTS_INDEX:
                return new EventFragment();
            case 1:
                return new EventFragment();
            case 2:
                return new DialogFragment();
            case 3:
                return new EventFragment();
            case 4:
                return new DialogFragment();
        }
        return new EventFragment();
    }

    @Override
    public int getCount() {
        return NUM_OF_FRAGMENTS;
    }
}
