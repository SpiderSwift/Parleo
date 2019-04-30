package com.leathersoft.parleo.fragment.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.network.model.EventPageAdapter;
import com.leathersoft.parleo.network.model.EventViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListFragment extends BaseFragment {

    private static final String TAG = "EventListFragment";

    LinearLayoutManager mLayoutManager;
    EventListAdapter mEventListAdapter;

    @BindView(R.id.recycler_view_events) RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events_list,container,false);
        ButterKnife.bind(this,v);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        EventViewModel eventViewModel = ViewModelProviders.of(this)
                .get(EventViewModel.class);

        final EventPageAdapter eventPageAdapter =
                new EventPageAdapter(mPushFragmentInterface);

        eventViewModel.getEventPagedList().observe(this, new Observer<PagedList<Event>>() {
            @Override
            public void onChanged(PagedList<Event> events) {
                eventPageAdapter.submitList(events);
            }
        });

        mRecyclerView.setAdapter(eventPageAdapter);


//        mLayoutManager = new LinearLayoutManager(getContext());
//
//        mEventListAdapter = new EventListAdapter(mPushFragmentInterface);
//        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(),mLayoutManager.getOrientation());
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mEventListAdapter);
//        mRecyclerView.addItemDecoration(decoration);

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public static EventListFragment newInstance(){
        EventListFragment fragment = new EventListFragment();
        return fragment;
    }
}
