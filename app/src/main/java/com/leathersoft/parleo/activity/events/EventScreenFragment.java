package com.leathersoft.parleo.activity.events;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


    @BindView(R.id.events_tablayout) TableLayout mTableLayout;
    @BindView(R.id.events_viewpager) ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_screen,container,false);
        ButterKnife.bind(this,v);

        EventsPagerAdapter adapter = new EventsPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
        return v;
    }
}
