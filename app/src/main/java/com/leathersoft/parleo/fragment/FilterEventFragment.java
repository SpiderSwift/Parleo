package com.leathersoft.parleo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.InterestsAdapter;
import com.leathersoft.parleo.messaging.Interest;
import com.leathersoft.parleo.util.ActionBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterEventFragment extends BaseFragment {

    @BindView(R.id.range_bar) CrystalRangeSeekbar seekbar;
    @BindView(R.id.tv_min) TextView min;
    @BindView(R.id.tv_max) TextView max;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.event_filter_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.event_filter_save:
                Snackbar.make(getView(),"TODO save event",Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

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

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.filter);
    }

    public static FilterEventFragment newInstance(){
        return new FilterEventFragment();
    }
}
