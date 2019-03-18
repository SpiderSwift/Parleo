package com.leathersoft.parleo.activity.events;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class EventsPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] mFragments = new Fragment[]{
            EventListFragment.newInstance(),
            EventListFragment.newInstance()
    };

    public EventsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "All events";
        }else{
            return "My events";
        }
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
