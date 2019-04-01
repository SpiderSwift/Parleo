package com.leathersoft.parleo.activity.events;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;

import com.leathersoft.parleo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventScreenFragment extends Fragment {

    @BindView(R.id.events_tablayout) TabLayout mTabLayout;
    @BindView(R.id.events_viewpager) ViewPager mViewPager;

    EventsPagerAdapter mEventsPagerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_screen,container,false);
        ButterKnife.bind(this,v);

        mEventsPagerAdapter = new EventsPagerAdapter(getFragmentManager());

        mViewPager.setAdapter(mEventsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return v;
    }

    public static EventScreenFragment newInstance(){
        return new EventScreenFragment();
    }
}
