package com.leathersoft.parleo.activity.events;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListFragment extends Fragment {

    @BindView(R.id.recycler_view_events) RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events_list,container,false);
        ButterKnife.bind(this,v);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        EventListAdapter mAdapter = new EventListAdapter(getActivity().getSupportFragmentManager());
        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(),layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(decoration);
        return v;
    }

    public static EventListFragment newInstance(){
        return new EventListFragment();
    }
}
