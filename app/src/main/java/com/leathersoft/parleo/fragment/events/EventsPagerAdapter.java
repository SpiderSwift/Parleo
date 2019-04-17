package com.leathersoft.parleo.fragment.events;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class EventsPagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> mFragmentList;

    public EventsPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(EventListFragment.newInstance());
        mFragmentList.add(EventListFragment.newInstance());
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
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
        return mFragmentList.size();
    }
}
