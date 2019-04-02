package com.leathersoft.parleo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventScreenFragment;
import com.leathersoft.parleo.adapter.InterestsAdapter;
import com.leathersoft.parleo.messaging.Interest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterEventFragment extends Fragment {

    @BindView(R.id.range_bar) CrystalRangeSeekbar seekbar;
    @BindView(R.id.tv_min) TextView min;
    @BindView(R.id.tv_max) TextView max;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_event, container, false);
        ButterKnife.bind(this, v);
        seekbar.setMaxValue(100);
        seekbar.setMinValue(0);
        seekbar.setMaxStartValue(50);
        seekbar.setMinStartValue(20);
        seekbar.apply();
        seekbar.setOnRangeSeekbarChangeListener((minValue, maxValue) -> {
            max.setText("" + maxValue.intValue());
            min.setText("" + minValue.intValue());
        });

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter();
        recyclerView.setAdapter(adapter);
        List<Interest> languages = new ArrayList<>();
        languages.add(new Interest("English", getActivity().getDrawable(R.drawable.ic_english)));
        languages.add(new Interest("Russian", getActivity().getDrawable(R.drawable.ic_russian)));
        languages.add(new Interest("Spanish", getActivity().getDrawable(R.drawable.ic_spanish)));
        languages.add(new Interest("English", getActivity().getDrawable(R.drawable.ic_english)));
        languages.add(new Interest("Russian", getActivity().getDrawable(R.drawable.ic_russian)));
        languages.add(new Interest("Spanish", getActivity().getDrawable(R.drawable.ic_spanish)));
        adapter.setInterests(languages);

        return v;
    }


    public static FilterEventFragment newInstance(){
        return new FilterEventFragment();
    }
}
