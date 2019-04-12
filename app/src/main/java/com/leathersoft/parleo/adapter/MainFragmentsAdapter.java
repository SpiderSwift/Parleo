package com.leathersoft.parleo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.leathersoft.parleo.activity.events.EventScreenFragment;
import com.leathersoft.parleo.fragment.EventFragment;
import com.leathersoft.parleo.fragment.DialogFragment;
import com.leathersoft.parleo.fragment.NotificationFragment;
import com.leathersoft.parleo.fragment.ProfileFragment;
import com.leathersoft.parleo.fragment.users.UserFragment;

public class MainFragmentsAdapter extends FragmentStatePagerAdapter {

    private static final int EVENTS_INDEX = 0;
    private static final int DIALOG_INDEX = 2;
    private static final int NOTIFICATION_INDEX = 3;
    private static final int NUM_OF_FRAGMENTS = 5;

    public MainFragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case EVENTS_INDEX:
                return EventScreenFragment.newInstance();
            case 1:
                return UserFragment.newInstance();
            case DIALOG_INDEX:
                return new DialogFragment();
            case NOTIFICATION_INDEX:
                return new NotificationFragment();
            case 4:
                return ProfileFragment.newInstance();
        }
        return new EventFragment();
    }

    @Override
    public int getCount() {
        return NUM_OF_FRAGMENTS;
    }

    public int getItemPosition(@NonNull Object object) { return POSITION_NONE; }
}
